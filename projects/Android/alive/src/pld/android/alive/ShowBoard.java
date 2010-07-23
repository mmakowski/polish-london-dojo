package pld.android.alive;

import java.util.concurrent.CountDownLatch;

import pld.gameoflife.Board;
import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;

public class ShowBoard extends Activity {
    

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BoardView boardView = (BoardView) findViewById(R.id.boardView);        
        final Board board = Board.gosperGliderGun(40, 40);
        board.addListener(boardView);        
//        final CountDownLatch latch = new CountDownLatch(1);
        boardView.getHolder().addCallback(new SurfaceHolder.Callback () {
        	@Override
        	public void surfaceCreated(SurfaceHolder holder) {        		
//        		latch.countDown();
        		new Thread(new Runnable(){
        			@Override
        			public void run() {
        				while(true){
        					board.nextTurn();
        					try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
        				}
        			}
        		}).start();
        		
        	}

        	@Override
        	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
        		board.nextTurn();
        		System.out.println("dupa");
        	}

        	@Override
        	public void surfaceDestroyed(SurfaceHolder holder) {
        		System.out.println("dupa2");
        	}    
        	});
//        try {
//			latch.await();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        board.nextTurn();
        
//        board.nextTurn();
//        try {
//			Thread.sleep(2000);
//	        board.nextTurn();
//	        Thread.sleep(2000);
//	        board.nextTurn();
//	        Thread.sleep(2000);
//
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    }
}

/*
// znajduje obiekt widoku na podstawie id:
BoardView boardView = (BoardView) findViewById(R.id.boardView);

// dodaje do widoku listener zdarzeń związanych z rysowaną powierzchnią
boardView.getHolder().addCallback(this); 

// a to jest interfejs tego listenera:

*/