package backtracking;

public interface ProblemaJ8<P, S> {
	P primoPuntoDiScelta();

	P prossimoPuntoDiScelta(P ps);

	P ultimoPuntoDiScelta();

	S primaScelta(P ps);

	S prossimaScelta(S s);

	S ultimaScelta(P ps);

	boolean assegnabile(S scelta, P puntoDiScelta);

	void assegna(S scelta, P puntoDiScelta);

	void deassegna(S scelta, P puntoDiScelta);

	P precedentePuntoDiScelta(P puntoDiScelta);

	S ultimaSceltaAssegnataA(P puntoDiScelta);

	void scriviSoluzione(int nr_sol);

	default  void risolvi(){
		risolvi(Integer.MAX_VALUE);
	}

	default void risolvi(int num_max_soluzioni) { // template method
		int nr_soluzione = 0;
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
			}// while( !backtrack ... )
				// backward
			fine = ps.equals(primoPuntoDiScelta())
					|| nr_soluzione == num_max_soluzioni;
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

