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
		String ArgDeclair;

		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);

				if (translatorBlock instanceof VariableNumberUnsignedLongBlock) {
						ArgDeclair = "long";
				} else {
						ArgDeclair = "int";
				}

		firstArg = ArgDeclair + " " + translatorBlock.toCode();
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);

				if (translatorBlock instanceof VariableNumberUnsignedLongBlock) {
						ArgDeclair = "long";
				} else {
						ArgDeclair = "int";
				}

		if (translatorBlock != null ) {
			secondArg = " , " + ArgDeclair + " " + translatorBlock.toCode();
		} else {
			secondArg = null;
		}
		String ret;
		ret = "void " + subroutineName + "( " + firstArg + secondArg + " )\n{\n";
		translatorBlock = getTranslatorBlockAtSocket(2);
		while (translatorBlock != null)
		{
			ret = ret + translatorBlock.toCode();
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
		ret = ret + "}\n\n";
		return ret;
	}
}
