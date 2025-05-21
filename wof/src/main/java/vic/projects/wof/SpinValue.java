package vic.projects.wof;

/* Core Java */
import java.lang.EnumConstantNotPresentException;
import java.util.regex.Pattern; // For custom patterns
import java.util.regex.Matcher; // To match my custom patterns
import java.util.ArrayList;

/**
* @desc This enum lists the possible values the final spin wheel can take on.
**/
public enum SpinValue
{
	FirstAinAmericas,
	MInAmericas,
	EInAmericas,
	R,
	IInAmericas,
	C,
	SecondAInAmericas,
	Apostrophe,
	SInAmericas,
	SingleStar,
	G,
	AInGAME,
	MInGAME,
	EInGAME,
	DoubleStar,
	SInSpin,
	P,
	IInSpin,
	NInSpin,
	Ampersand,
	W,
	IInWIN,
	NInWIN,
	TripleStar;

	public static ArrayList<String> getSpinNames()
	{
		ArrayList<String> toRet = new ArrayList<String>();

		for (SpinValue sv : SpinValue.values())
		{
			toRet.add(sv.name());
		}

		return toRet;
	}

	public static SpinValue strToVal(String name) throws EnumConstantNotPresentException
	{
		/* All of the patterns we need */
		Pattern firstAInAmericasPat = Pattern.compile("^(Fir|1)st +A +in +AMERICA('S)?$", Pattern.CASE_INSENSITIVE);
		Pattern mInAmericasPat = Pattern.compile("^M +(in|of) +AMERICA('S)?$", Pattern.CASE_INSENSITIVE);
		Pattern eInAmericasPat = Pattern.compile("^e +(in|of) +america('s)?$", Pattern.CASE_INSENSITIVE);
		Pattern rPat = Pattern.compile("^r{1}+|r +(in|of) +america('s)?$", Pattern.CASE_INSENSITIVE);
		Pattern iInAmericasPat = Pattern.compile("^i +(in|of) +AMERICA('S)?|I in ICA$", Pattern.CASE_INSENSITIVE);
		Pattern cPat = Pattern.compile("^c{1}|c (of|in) america('s)?+$", Pattern.CASE_INSENSITIVE);
		Pattern secondAInAmericasPat = Pattern.compile("^((Seco|2)nd|Last|Final) +'?A'? +(in|of) +AMERICA('S)?$", Pattern.CASE_INSENSITIVE);
		Pattern aposPat = Pattern.compile("^'|apostrophe$", Pattern.CASE_INSENSITIVE);
		Pattern sInAmericasPat = Pattern.compile("^S +(in|of) +AMERICA'S$", Pattern.CASE_INSENSITIVE);
		Pattern singleStarPat = Pattern.compile("^Single +(star|asterisk|\\*)|(1|One|Lone) +star|\\* before game|\\* after America's|\\* between America's and Game|\\*$", Pattern.CASE_INSENSITIVE);
		Pattern gPat = Pattern.compile("^g{1}+$", Pattern.CASE_INSENSITIVE);
		Pattern aInGamePat = Pattern.compile("^a +(in|of) +game$", Pattern.CASE_INSENSITIVE);
		Pattern mInGamePat = Pattern.compile("^m +(in|of) +game$", Pattern.CASE_INSENSITIVE);
		Pattern eInGamePat = Pattern.compile("^e +(in|of) +game$", Pattern.CASE_INSENSITIVE);
		Pattern doubleStarPat = Pattern.compile("^(double +star|2 +stars|two stars between game and show|\\*\\* 2 stars|two stars|\\*\\*||\\*\\* between Game and Spin)$", Pattern.CASE_INSENSITIVE);
		Pattern sInSpinPat = Pattern.compile("^s +(in|of) +spin$", Pattern.CASE_INSENSITIVE);
		Pattern pPat = Pattern.compile("^p{1}+|p +(of|in) +spin$", Pattern.CASE_INSENSITIVE);
		Pattern iInSpinPat = Pattern.compile("^i +(in|of) +spin$", Pattern.CASE_INSENSITIVE);
		Pattern nInSpinPat = Pattern.compile("^n +(in|of) +spin$", Pattern.CASE_INSENSITIVE);
		Pattern ampPat = Pattern.compile("^(ampersand|&|& between Spin & Win)$", Pattern.CASE_INSENSITIVE);
		Pattern wPat = Pattern.compile("^(w{1}+|w (in|of) win)$", Pattern.CASE_INSENSITIVE);
		Pattern iInWinPat = Pattern.compile("^i +(in|of) +win$", Pattern.CASE_INSENSITIVE);
		Pattern nInWinPat = Pattern.compile("^n{1} *(in|of) *win$", Pattern.CASE_INSENSITIVE);
		Pattern tripleStarPat = Pattern.compile("^((t(riple|hree)|3) +star[s]?)|\\*\\*\\*$", Pattern.CASE_INSENSITIVE);

		/* Clean up the string */
		String trimmedName = name.trim(); // Remove leading and trailing whitespace

		/* All of the matchers we need */
		Matcher firstAInAmericasMat = firstAInAmericasPat.matcher(trimmedName);
		Matcher mInAmericasMat = mInAmericasPat.matcher(trimmedName);
		Matcher eInAmericasMat = eInAmericasPat.matcher(trimmedName);
		Matcher rMat = rPat.matcher(trimmedName);
		Matcher iInAmericasMat = iInAmericasPat.matcher(trimmedName);
		Matcher cMat = cPat.matcher(trimmedName);
		Matcher secondAInAmericasMat = secondAInAmericasPat.matcher(trimmedName);
		Matcher aposMat = aposPat.matcher(trimmedName);
		Matcher sInAmericasMat = sInAmericasPat.matcher(trimmedName);
		Matcher singleStarMat = singleStarPat.matcher(trimmedName);
		Matcher	gMat = gPat.matcher(trimmedName);
		Matcher	aInGameMat = aInGamePat.matcher(trimmedName);
		Matcher	mInGameMat = mInGamePat.matcher(trimmedName);
		Matcher	eInGameMat = eInGamePat.matcher(trimmedName);
		Matcher doubleStarMat = doubleStarPat.matcher(trimmedName);
		Matcher sInSpinMat = sInSpinPat.matcher(trimmedName);
		Matcher	pMat = pPat.matcher(trimmedName);
		Matcher	iInSpinMat = iInSpinPat.matcher(trimmedName);
		Matcher	nInSpinMat = nInSpinPat.matcher(trimmedName);
		Matcher ampMat = ampPat.matcher(trimmedName);
		Matcher	wMat = wPat.matcher(trimmedName);
		Matcher	iInWinMat = iInWinPat.matcher(trimmedName);
		Matcher nInWinMat = nInWinPat.matcher(trimmedName);
		Matcher tripleStarMat = tripleStarPat.matcher(trimmedName);

		if (firstAInAmericasMat.matches()) // First A in AMERICA'S
		{
			return FirstAinAmericas;
		}

		else if (mInAmericasMat.matches()) // M in AMERICA'S
		{
			return MInAmericas;
		}

		else if (eInAmericasMat.matches()) // E in AMERICA'S
		{
			return EInAmericas;
		}

		else if (rMat.matches()) // R or "R in AMERICA'S"
		{
			return R;
		}

		else if (iInAmericasMat.matches()) // I in AMERICA'S
		{
			return IInAmericas;
		}

		else if (cMat.matches()) // C
		{
			return C;
		}

		else if (secondAInAmericasMat.matches()) // Second A in AMERICA'S
		{
			return SecondAInAmericas;
		}

		else if (aposMat.matches()) // Apostrophe
		{
			return Apostrophe;
		}

		else if (sInAmericasMat.matches()) // S in AMERICA'S
		{
			return SInAmericas;
		}

		else if (singleStarMat.matches()) // Single star
		{
			return SingleStar;
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

		else if (doubleStarMat.matches()) // Double star
		{
			return DoubleStar;
		}

		else if (sInSpinMat.matches()) // S in SPIN
		{
			return SInSpin;
		}

		else if (pMat.matches()) // P
		{
			return P;
		}

		else if (iInSpinMat.matches()) // I in SPIN
		{
			return IInSpin;
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

		else if (tripleStarMat.matches()) // 3 stars
		{
			return TripleStar;
		}

		else // Error
		{
			throw new EnumConstantNotPresentException(SpinValue.class, "\"" + name + "\" is not a valid entry on Wheel of Fortune's Final Spin wheel");
		}
	}
};
