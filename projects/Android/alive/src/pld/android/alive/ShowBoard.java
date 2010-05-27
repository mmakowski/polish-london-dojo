package pld.android.alive;

import pld.gameoflife.Board;
import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;

public class ShowBoard extends Activity implements SurfaceHolder.Callback {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BoardView boardView = (BoardView) findViewById(R.id.boardView);
        boardView.getHolder().addCallback(this); 
    }
    
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
        final Board board = Board.gosperGliderGun(40, 40);
        BoardView boardView = (BoardView) findViewById(R.id.boardView);
        board.addListener(boardView);
        Thread t = new Thread() {
        	public void run() {
                while (true) {
                	board.nextTurn();
                	try {
        				Thread.sleep(100);
        			} catch (InterruptedException e) {
        				e.printStackTrace();
        			}
                }
        	}
        };
        t.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}    
}