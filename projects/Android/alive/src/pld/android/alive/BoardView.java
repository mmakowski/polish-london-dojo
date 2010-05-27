package pld.android.alive;

import pld.gameoflife.Board;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BoardView extends SurfaceView implements Board.Listener {
	private static final Paint ALIVE = createPaint(Color.GREEN);
	private static final Paint DEAD = createPaint(Color.WHITE);
	
	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onBoardUpdated(Board board) {
		Canvas canvas = null;
		SurfaceHolder holder = getHolder();
		try {
			canvas = holder.lockCanvas(null);
			int sideLen = Math.min(canvas.getWidth() / board.getWidth(), canvas.getHeight() / board.getHeight());
			synchronized (holder) {
				for (int y = 0; y < board.getHeight(); y++) {
					for (int x = 0; x < board.getWidth(); x ++) {
						canvas.drawRect(new Rect(x * sideLen, y * sideLen, (x+1) * sideLen, (y+1) * sideLen), board.isAliveAt(x, y) ? ALIVE : DEAD);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				holder.unlockCanvasAndPost(canvas);
			}
		}	
	}

	private static Paint createPaint(int colour) {
		Paint paint = new Paint();
		paint.setColor(colour);
		paint.setStyle(Style.FILL);
		return paint;
	}

}
