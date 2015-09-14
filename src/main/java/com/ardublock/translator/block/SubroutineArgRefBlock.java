package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class SubroutineArgRefBlock extends TranslatorBlock
{

	public SubroutineArgRefBlock(Long blockId, Translator translator,
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
		String ret;

		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
			firstArg = translatorBlock.toCode();

		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
			secondArg = translatorBlock.toCode();

		translatorBlock = this.getRequiredTranslatorBlockAtSocket(2);
			thirdArg = translatorBlock.toCode();

		if (!translator.containFunctionName(subroutineName))
		{
			throw new SubroutineNotDeclaredException(blockId);
		}

		ret = "\t"+subroutineName + "(" + firstArg + " , " + secondArg + " , " + thirdArg + ");\n";
		ret = ret.replace("boolean& ","").replace("char& ","").replace("char* ","").replace("String& ","");

		return ret;
	}

}
