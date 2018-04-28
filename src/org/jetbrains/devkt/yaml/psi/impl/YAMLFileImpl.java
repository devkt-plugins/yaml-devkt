package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.kotlin.com.intellij.extapi.psi.PsiFileBase;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.kotlin.com.intellij.psi.FileViewProvider;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.devkt.yaml.YAMLElementTypes;
import org.jetbrains.devkt.yaml.YAMLFileType;
import org.jetbrains.devkt.yaml.YAMLLanguage;
import org.jetbrains.devkt.yaml.psi.YAMLDocument;
import org.jetbrains.devkt.yaml.psi.YAMLFile;
import org.jetbrains.devkt.yaml.psi.YAMLPsiElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oleg
 */
public class YAMLFileImpl extends PsiFileBase implements YAMLFile {
	public YAMLFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, YAMLLanguage.INSTANCE);
	}

	@NotNull
	public FileType getFileType() {
		return YAMLFileType.YML;
	}

	@Override
	public String toString() {
		return "YAML file";
	}

	public List<YAMLDocument> getDocuments() {
		final ArrayList<YAMLDocument> result = new ArrayList<>();
		for (ASTNode node : getNode().getChildren(TokenSet.create(YAMLElementTypes.DOCUMENT))) {
			result.add((YAMLDocument) node.getPsi());
		}
		return result;
	}

	public List<YAMLPsiElement> getYAMLElements() {
		final ArrayList<YAMLPsiElement> result = new ArrayList<>();
		for (ASTNode node : getNode().getChildren(null)) {
			final PsiElement psi = node.getPsi();
			if (psi instanceof YAMLPsiElement) {
				result.add((YAMLPsiElement) psi);
			}
		}
		return result;
	}
}
