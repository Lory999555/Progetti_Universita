package backtracking;

public abstract class Problema<P, S> {
	protected abstract P primoPuntoDiScelta();

	protected abstract P prossimoPuntoDiScelta(P ps);

	protected abstract P ultimoPuntoDiScelta();

	protected abstract S primaScelta(P ps);

	protected abstract S prossimaScelta(S s);

	protected abstract S ultimaScelta(P ps);

	protected abstract boolean assegnabile(S scelta, P puntoDiScelta);

	protected abstract void assegna(S scelta, P puntoDiScelta);

	protected abstract void deassegna(S scelta, P puntoDiScelta);

	protected abstract P precedentePuntoDiScelta(P puntoDiScelta);

	protected abstract S ultimaSceltaAssegnataA(P puntoDiScelta);

	protected abstract void scriviSoluzione(int nr_sol);

	private int num_max_soluzioni, nr_soluzione = 0;

	public Problema(int num_max_soluzioni) {
		this.num_max_soluzioni = num_max_soluzioni;
	}

	public Problema() {
		this(Integer.MAX_VALUE);
	}

	public void risolvi() { // template method
		P ps = primoPuntoDiScelta();
		S s = primaScelta(ps);
		boolean backtrack = false, fine = false;
		do {
			// forward
			while (!backtrack && nr_soluzione < num_max_soluzioni) {

				if (assegnabile(s, ps)) {
					assegna(s, ps);
					if (ps.equals(ultimoPuntoDiScelta())) {
						++nr_soluzione;
						scriviSoluzione(nr_soluzione);
						deassegna(s, ps);
						if (!s.equals(ultimaScelta(ps)))
							s = prossimaScelta(s);
						else
							backtrack = true;
					} else {
						ps = prossimoPuntoDiScelta(ps);
						s = primaScelta(ps);
					}
				} else if (!s.equals(ultimaScelta(ps)))
					s = prossimaScelta(s);
				else
					backtrack = true;
			} // while( !backtrack ... )

			// backward
			fine = ps.equals(primoPuntoDiScelta()) || nr_soluzione == num_max_soluzioni;
			while (backtrack && !fine) {
				ps = precedentePuntoDiScelta(ps);
				s = ultimaSceltaAssegnataA(ps);
				deassegna(s, ps);
				if (!s.equals(ultimaScelta(ps))) {
					s = prossimaScelta(s);
					backtrack = false;
				} else if (ps.equals(primoPuntoDiScelta()))
					fine = true;
			}
		} while (!fine);
	}// risolvi

}

