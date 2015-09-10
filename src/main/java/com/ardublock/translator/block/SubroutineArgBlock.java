package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class SubroutineArgBlock extends TranslatorBlock
{

	public SubroutineArgBlock(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String subroutineName = label.trim();

		String firstArg;
		String secondArg;
		String thirdArg;
		String ArgDeclair;

		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		firstArg = ArgDeclair(translatorBlock) + translatorBlock.toCode();

		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		secondArg = ArgDeclair(translatorBlock) + translatorBlock.toCode();

		translatorBlock = this.getRequiredTranslatorBlockAtSocket(2);
		thirdArg  = ArgDeclair(translatorBlock) + translatorBlock.toCode();

		String ret;
		ret = "void " + subroutineName + "(" + firstArg + " , " + secondArg + " , " + thirdArg + ")\n{\n";
		translatorBlock = getTranslatorBlockAtSocket(3);
		while (translatorBlock != null)
		{
			ret = ret + translatorBlock.toCode();
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
		ret = ret + "}\n\n";
		return ret;
	}

	private String ArgDeclair( TranslatorBlock translatorBlock ) {

		String argDeclair;

		if (translatorBlock instanceof VariableNumberUnsignedLongBlock) {
			argDeclair = "unsigned long&";
		} else if (translatorBlock instanceof VariableNumberDoubleBlock) {
			argDeclair = "double&";
		} else if (translatorBlock instanceof VariableDigitalBlock) {
			argDeclair = "boolean&";
		} else if (translatorBlock instanceof VariablePolyBlock) {
			argDeclair = "char&";
		} else if (translatorBlock instanceof VariableStringBlock) {
			argDeclair = "char*";
		} else if (translatorBlock instanceof variable_String) {
			argDeclair = "String&";
		} else {
			argDeclair = "int&";
		}
		return argDeclair;
	}
}
