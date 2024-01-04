package observer;

import board.Board;

public interface Observer {
    double rateModifier = 0.05; // TODO o tyle będzie rósł kurs wydanej waluty (DO MODYFIKACJI)

    // Dwie wersje update() dla łatwiejszej implementacji
    void updateDollar(Board board);
    void updateEuro(Board board);


} 