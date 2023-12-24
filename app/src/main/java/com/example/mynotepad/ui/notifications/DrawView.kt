package com.example.mynotepad.ui.notifications

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.roundToInt

class DrawView(context:Context, attrs:AttributeSet) : View(context,attrs) {
    private val paintTails= Paint()
    private val paintTailsStroke= Paint()
    private val paintSquare= Paint()
    private val paintSquareBorder= Paint()
    private val paintMarkerLine= Paint()
    private val paintMarkerText= Paint()

    //private val paintSquareBorder= Paint()
    var sizeTile=420f
    var sizeSquare=1500f
    var sizeGap=6f
    var leftTile=20f

    private var amountTilesFl=0f// sizeSquare.toFloat()/(sizeTile+sizeGap)+1
    var amountTiles=0//amountTilesFl.roundToInt()
   // Log.v("a","drawView  amountTiles= $amountTiles" )
   var sizeTileDpWithGap=0//(width-40)/amountTiles
    var scale:Float =0f// sizeTileDpWithGap.toFloat()/(sizeTile.toFloat()+sizeGap.toFloat())
    var sizeTileDp=0//sizeTile*scale
    var sizeSquareDp=0//sizeSquare*scale
    var sizeGapDp=0//sizeGap*scale



    var leftSquare=0f//20f+sizeTileDp/2
    var rightSquare=0f//leftSquare+sizeSquareDp
    val topSquare=50f
    var bottomSquare=height-50f
    //val bottomSquare=width.coerceAtMost(height)/2f

    val pts:FloatArray = floatArrayOf( 5f,10f, 25f,50f, 125f,50f,35f,80f)

    var rightTile=0f//20f+sizeTileDp
    var topTile=70f
    var bottomTile=height-70f
    private val topMarkerLineY=topTile
    private var bottomMarkerLineY=bottomTile+30
    //private  var markerLineX=0f
    private var positionMarker=0f




    fun calcSize(atrib:Atrib){
sizeTile=atrib.sizeTile
        sizeSquare=atrib.sizeSquare
        sizeGap=atrib.sizeGap
amountTilesFl=sizeSquare/(sizeTile+sizeGap)+1
     amountTiles=amountTilesFl.roundToInt()
        sizeTileDpWithGap=(width-40)/amountTiles
        scale = sizeTileDpWithGap/(sizeTile+sizeGap)
        sizeTileDp=(sizeTile*scale).toInt()
        sizeSquareDp=(sizeSquare*scale).toInt()
        sizeGapDp=(sizeGap*scale).toInt()

        leftSquare=20f+sizeTileDp/2
        rightSquare=leftSquare+sizeSquareDp
        bottomSquare=height-50f
        rightTile=20f+sizeTileDp
        bottomTile=height-70f

        if (atrib.alignmentStart){
            leftSquare=20f+sizeTileDp/2
            rightSquare=leftSquare+sizeSquareDp
            leftTile=leftSquare
            rightTile=leftTile+sizeTileDp
        }else if(atrib.alignmentCenter){
            leftSquare=(width-sizeSquareDp.toFloat())/2
            rightSquare=leftSquare+sizeSquareDp
            leftTile=(width/2)%sizeTileDp.toFloat()
            rightTile=leftTile+sizeTileDp
            Log.v("a","drawView  leftTile= $leftTile" )
        }else if(atrib.alignmentMiddle){
            leftSquare=(width-sizeSquareDp.toFloat())/2
            rightSquare=leftSquare+sizeSquareDp
            leftTile=(width/2)%sizeTileDp.toFloat()-sizeTileDp/2
            rightTile=leftTile+sizeTileDp
        }
      //  markerLineX=rightTile
        positionMarker=rightTile
        bottomMarkerLineY=bottomTile+50
//        topTile=40f
//        bottomTile=height-40f

    }

    fun moveMarkerToLeft(){
        if (positionMarker-sizeTileDp-sizeGapDp>leftSquare){

            positionMarker=positionMarker-sizeTileDp-sizeGapDp
            invalidate()
        }
    }
    fun moveMarkerToRight(){
        if(positionMarker+sizeTileDp+sizeGapDp<rightSquare){

            positionMarker=positionMarker+sizeTileDp+sizeGapDp
            invalidate()
        }
    }

    fun calcMarkerPosition(){
       //positionMarker=rightTile
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_UP-> {
                Log.v("a", "drawView  event.action= ACTION_UP")
               // Log.v("a", "drawView  event.x= ACTION_UP")
                return true

            }
            MotionEvent.ACTION_DOWN-> {
                Log.v("a", "drawView  event.action= ACTION_DOWN")
                Log.v("a", "drawView  event.x= ${event.x}")
                return true
            }
            MotionEvent.ACTION_MOVE-> {
                Log.v("a", "drawView  event.action=ACTION_MOVE")
                return true

            }
            MotionEvent.ACTION_OUTSIDE-> {
                Log.v("a", "drawView  event.action= ACTION_OUTSIDE")
                return true

            }

        }
       // Log.v("a","drawView  event.action= ${event?.action}" )
        return super.onTouchEvent(event)
    }



    init {
        Log.v("a","drawView  sizeTileDp= $sizeTileDp" )
        // start setting parametr size tile, square

        val atrib:Atrib= Atrib(250f, 1520f, 3f, false,false,true)

        calcSize(atrib)

        paintSquare.style=Paint.Style.FILL_AND_STROKE
        paintSquare.color=Color.DKGRAY
        paintSquare.strokeWidth=3f

        paintSquareBorder.style=Paint.Style.STROKE
        paintSquareBorder.color=Color.YELLOW
        paintSquareBorder.strokeWidth=3f


        paintTails.style=Paint.Style.FILL_AND_STROKE
        paintTails.color=Color.WHITE
        paintTails.strokeWidth=3f

        paintTailsStroke.style=Paint.Style.STROKE
        paintTailsStroke.color=Color.BLACK
        paintTailsStroke.strokeWidth=1f

        paintMarkerLine.style=Paint.Style.STROKE
        paintMarkerLine.color=Color.RED
        paintMarkerLine.strokeWidth=5f

        paintMarkerText.style=Paint.Style.STROKE
        paintMarkerText.color=Color.WHITE
        paintMarkerText.strokeWidth=1f
        paintMarkerText.textSize= 25F


    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (scale<=0f){
            val atrib=Atrib(sizeTile, sizeSquare, sizeGap,true,false, false  )
                    calcSize(atrib)
        }


//        val amountTilesFl=sizeSquare.toFloat()/(sizeTile+sizeGap)+1
//        val amountTiles=amountTilesFl.roundToInt()
//        Log.v("a","drawView  amountTiles= $amountTiles" )
//        val sizeTileDpWithGap=(width-40)/amountTiles
//        val scale:Float = sizeTileDpWithGap.toFloat()/(sizeTile.toFloat()+sizeGap.toFloat())
//        val sizeTileDp=sizeTile*scale
//        val sizeSquareDp=sizeSquare*scale
//        val sizeGapDp=sizeGap*scale
//
//
//
//        val leftSquare=20f+sizeTileDp/2
//        val rightSquare=leftSquare+sizeSquareDp
//        val topSquare=20f
//        val bottomSquare=height-20f
//
//
//        //val bottomSquare=width.coerceAtMost(height)/2f
//
//        val pts:FloatArray = floatArrayOf( 5f,10f, 25f,50f, 125f,50f,35f,80f)
//
//        val rightTile=20f+sizeTileDp
//        val topTile=40f
//        val bottomTile=height-40f
//        canvas.drawRoundRect (leftSquare,topSquare,rightSquare,bottomSquare,5f,5f, paintSquare )
//        Log.v("a","drawView  sizeTileDp= $sizeTileDp" )
//        Log.v("a","drawView  sizeGapDp= $sizeGapDp" )
for (i in 1..amountTiles){
    canvas.drawRoundRect (leftTile+sizeTileDp*(i-1)+sizeGapDp*(i-1),topTile,rightTile+sizeTileDp*(i-1)+sizeGapDp*(i-1),bottomTile,5f,5f, paintTails )
    canvas.drawRoundRect (leftTile+sizeTileDp*(i-1)+sizeGapDp*(i-1),topTile,rightTile+sizeTileDp*(i-1)+sizeGapDp*(i-1),bottomTile,5f,5f, paintTailsStroke )

    Log.v("a","drawView  leftTile+sizeTileDp*(i-1)+2*i= ${leftTile + sizeTileDp * (i - 1) + 2 * i}" )

}
        canvas.drawRoundRect (leftSquare,topSquare,rightSquare,bottomSquare,5f,5f, paintSquareBorder )
        canvas.drawLine(positionMarker, topMarkerLineY, positionMarker ,bottomMarkerLineY, paintMarkerLine)

        canvas.drawText("${((positionMarker-leftSquare+sizeGapDp)/scale).roundToInt()}", positionMarker-70,height-20f,paintMarkerText)



    }

    fun setAlignmentStart(){
        val atrib= Atrib(sizeTile, sizeSquare, sizeGap, alignmentStart = true, alignmentCenter = false, alignmentMiddle = false  )
        calcSize(atrib)
        invalidate()
    }

    fun setAlignmentCenter(){
        val atrib= Atrib(sizeTile, sizeSquare, sizeGap, alignmentStart = false, alignmentCenter = true, alignmentMiddle = false  )
        calcSize(atrib)
        invalidate()
    }

    fun setAlignmentMiddle(){
        val atrib= Atrib(sizeTile, sizeSquare, sizeGap, alignmentStart = false, alignmentCenter = false, alignmentMiddle = true  )
        calcSize(atrib)
        invalidate()
    }

    fun changeTile(size:Float){
        val atrib= Atrib(size, sizeSquare, sizeGap, alignmentStart = false, alignmentCenter = false, alignmentMiddle = true  )
        calcSize(atrib)
        invalidate()
    }

    fun changeSquare(size:Float){
        val atrib= Atrib(sizeTile, size, sizeGap, alignmentStart = false, alignmentCenter = false, alignmentMiddle = true  )
        calcSize(atrib)
        //sizeSquare=size

        invalidate()
    }
    fun changeGap(size:Float){
        val atrib= Atrib(sizeTile, sizeSquare, size, alignmentStart = false, alignmentCenter = false, alignmentMiddle = true  )
        calcSize(atrib)
        invalidate()
    }
    class Atrib(sizeTile:Float, sizeSquare:Float, sizeGap:Float, alignmentStart:Boolean, alignmentCenter:Boolean, alignmentMiddle:Boolean ) {
        val sizeTile:Float=sizeTile
        val sizeSquare:Float=sizeSquare
        val sizeGap:Float=sizeGap
        val alignmentStart:Boolean=alignmentStart
        val alignmentCenter:Boolean=alignmentCenter
        val alignmentMiddle:Boolean=alignmentMiddle


    }

}