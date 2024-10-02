package vic.projects.wof;

/* Core Java */
import java.lang.EnumConstantNotPresentException;
import java.util.regex.Pattern; // For custom patterns
import java.util.regex.Matcher; // To match my custom patterns

/**
* @desc This enum lists the possible values the final spin wheel can take on.
**/
public enum SpinValue
{
	FirstAinAMERICAS,
	MinAMERICAS,
	EinAMERICAS,
	R,
	IInAMERICAS,
	C,
	SecondAInAMERICAS,
	Apostrophe,
	SInAMERICAS,
	SingleStar,
	G,
	AInGAME,
	MInGAME,
	EInGAME,
	DoubleStar,
	SinSPIN,
	P,
	IInSPIN,
	NInSpin,
	Ampersand,
	W,
	IInWIN,
	NInWIN,
	TripleStar;

	public static SpinValue strToVal(String name) throws EnumConstantNotPresentException
	{
		/* All of the patterns we need */
		Pattern firstAInAMERICASPat = Pattern.compile("^First +A +in +AMERICA'S$", Pattern.CASE_INSENSITIVE);
		Pattern rPat = Pattern.compile("^r{1}+$", Pattern.CASE_INSENSITIVE);
		Pattern iInAMERICASPat = Pattern.compile("^i +in +AMERICA'S$", Pattern.CASE_INSENSITIVE);
		Pattern cPat = Pattern.compile("^c{1}+$", Pattern.CASE_INSENSITIVE);
		Pattern aposPat = Pattern.compile("^'|apostrophe$", Pattern.CASE_INSENSITIVE);
		Pattern gPat = Pattern.compile("^g{1}+$", Pattern.CASE_INSENSITIVE);
		Pattern aInGamePat = Pattern.compile("^a +in +game$", Pattern.CASE_INSENSITIVE);
		Pattern mInGamePat = Pattern.compile("^m +in +game$", Pattern.CASE_INSENSITIVE);
		Pattern eInGamePat = Pattern.compile("^e +in +game$", Pattern.CASE_INSENSITIVE);
		Pattern pPat = Pattern.compile("^p{1}+$", Pattern.CASE_INSENSITIVE);
		Pattern nInSpinPat = Pattern.compile("^n +in +spin$", Pattern.CASE_INSENSITIVE);
		Pattern ampPat = Pattern.compile("^(ampersand|&)$", Pattern.CASE_INSENSITIVE);
		Pattern wPat = Pattern.compile("^w{1}+$", Pattern.CASE_INSENSITIVE);
		Pattern iInWinPat = Pattern.compile("^i +in +win$", Pattern.CASE_INSENSITIVE);
		Pattern nInWinPat = Pattern.compile("^n{1} *in *win$", Pattern.CASE_INSENSITIVE);

		/* All of the matchers we need */
		Matcher firstAInAMERICASMat = firstAInAMERICASPat.matcher(name);
		Matcher rMat = rPat.matcher(name);
		Matcher iInAMERICASMat = iInAMERICASPat.matcher(name);
		Matcher cMat = cPat.matcher(name);
		Matcher	gMat = gPat.matcher(name);
		Matcher	aInGameMat = aInGamePat.matcher(name);
		Matcher	mInGameMat = mInGamePat.matcher(name);
		Matcher	eInGameMat = eInGamePat.matcher(name);
		Matcher	pMat = pPat.matcher(name);
		Matcher	nInSpinMat = nInSpinPat.matcher(name);
		Matcher ampMat = ampPat.matcher(name);
		Matcher	wMat = wPat.matcher(name);
		Matcher	iInWinMat = iInWinPat.matcher(name);
		Matcher nInWinMat = nInWinPat.matcher(name);
		Matcher aposMat = aposPat.matcher(name);

		if (firstAInAMERICASMat.matches()) // First A in AMERICA'S
		{
			return FirstAinAMERICAS;
		}

		else if (rMat.matches()) // R
		{
			return R;
		}

		else if (iInAMERICASMat.matches()) // I in AMERICA'S
		{
			return IInAMERICAS;
		}

		else if (cMat.matches()) // C
		{
			return C;
		}

		else if (aposMat.matches()) // Apostrophe
		{
			return Apostrophe;
		}

		else if (gMat.matches()) // G
		{
			return G;
		}

		else if (aInGameMat.matches()) // A in GAME
		{
			return AInGAME;
		}

		else if (mInGameMat.matches()) // M in GAME
		{
			return MInGAME;
		}

		else if (eInGameMat.matches()) // E in GAME
		{
			return EInGAME;
		}

		else if (pMat.matches()) // P
		{
			return P;
		}

		else if (nInSpinMat.matches()) // N in SPIN
		{
			return NInSpin;
		}

		else if (ampMat.matches()) // &
		{
			return Ampersand;
		}

		else if (wMat.matches()) // W in WIN
		{
			return W;
		}

		else if (iInWinMat.matches()) // I in WIN
		{
			return IInWIN;
		}

		else if (nInWinMat.matches()) // N in WIN
		{
			return NInWIN;
		}

		else // Error
		{
			throw new EnumConstantNotPresentException(SpinValue.class, "\"" + name + "\" is not a valid entry on Wheel of Fortune's Final Spin wheel");
		}
	}
};
