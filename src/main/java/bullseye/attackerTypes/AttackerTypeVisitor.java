package bullseye.attackerTypes;

public interface AttackerTypeVisitor {
	double getApplicability(Criminal criminal);
	double getApplicability(Intelligence intelligence);
	double getApplicability(Terrorist terrorist);
}
