package com.example.mynotepad.ui.notifications

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.roundToInt

class DrawView(context:Context, attrs:AttributeSet) : View(context,attrs) {
    private val paintTails= Paint()
    private val paintSquare= Paint()
    private val paintSquareBorder= Paint()
    var sizeTile=420

    init {
        paintSquare.style=Paint.Style.FILL_AND_STROKE
        paintSquare.color=Color.DKGRAY
        paintSquare.strokeWidth=3f

        paintSquareBorder.style=Paint.Style.STROKE
        paintSquareBorder.color=Color.YELLOW
        paintSquareBorder.strokeWidth=3f


        paintTails.style=Paint.Style.FILL_AND_STROKE
        paintTails.color=Color.WHITE
        paintTails.strokeWidth=3f
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val sizeSquare=1500

        val sizeGap=6
        val amountTilesFl=sizeSquare.toFloat()/(sizeTile+sizeGap)+1
        val amountTiles=amountTilesFl.roundToInt()
        Log.v("a","drawView  amountTiles= $amountTiles" )
        val sizeTileDpWithGap=(width-40)/amountTiles
        val scale:Float = sizeTileDpWithGap.toFloat()/(sizeTile.toFloat()+sizeGap.toFloat())
        val sizeTileDp=sizeTile*scale
        val sizeSquareDp=sizeSquare*scale
        val sizeGapDp=sizeGap*scale



        val leftSquare=20f+sizeTileDp/2
        val rightSquare=leftSquare+sizeSquareDp
        val topSquare=20f
        val bottomSquare=height-20f
        //val bottomSquare=width.coerceAtMost(height)/2f

        val pts:FloatArray = floatArrayOf( 5f,10f, 25f,50f, 125f,50f,35f,80f)

        val leftTile=20f
        val rightTile=20f+sizeTileDp
        val topTile=40f
        val bottomTile=height-40f
        canvas.drawRoundRect (leftSquare,topSquare,rightSquare,bottomSquare,5f,5f, paintSquare )
        Log.v("a","drawView  sizeTileDp= $sizeTileDp" )
        Log.v("a","drawView  sizeGapDp= $sizeGapDp" )
for (i in 1..amountTiles){
    canvas.drawRoundRect (leftTile+sizeTileDp*(i-1)+sizeGapDp*(i-1),topTile,rightTile+sizeTileDp*(i-1)+sizeGapDp*(i-1),bottomTile,5f,5f, paintTails )
    Log.v("a","drawView  leftTile+sizeTileDp*(i-1)+2*i= ${leftTile + sizeTileDp * (i - 1) + 2 * i}" )

}
        canvas.drawRoundRect (leftSquare,topSquare,rightSquare,bottomSquare,5f,5f, paintSquareBorder )


    }


    fun changeTile(newSizeTile:Int){
        sizeTile=newSizeTile
        invalidate()
    }
}