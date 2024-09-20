package vic.projects.wof;

/* Core Java */
import java.lang.EnumConstantNotPresentException;

/**
* @desc This enum lists the possible values the final spin wheel can take on.
**/
public enum SpinValue
{
	FirstAinAMERICAS,
	MinAMERICAS,
	EinAMERICAS,
	R,
	I,
	C,
	SecondAInAMERICAS,
	Apostrophe,
	SInAMERICAS,
	SingleStar,
	G,
	AinGAME,
	MinGAME,
	EinGAME,
	DoubleStar,
	SinSPIN,
	P,
	IinSPIN,
	NinSPIN,
	Ampersand,
	W,
	IinWIN,
	NinWIN,
	TripleStar;

	public SpinValue strToVal(String name) throws EnumConstantNotPresentException
	{
		if (name.matches("^[rR]{1}+$")) // R
		{
			return R;
		}

		else if (name.matches("^[wW]{1}+$")) // W in WIN
		{
			return W;
		}

		else // Error
		{
			throw new EnumConstantNotPresentException(SpinValue.class, "\"" + name + "\" is not a valid entry on Wheel of Fortune's Final Spin wheel");
		}
	}
};
