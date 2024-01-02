package com.example.mynotepad.ui.notifications

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.IBinder
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
    var sizeTileDp=0f//sizeTile*scale
    var sizeSquareDp=0f//sizeSquare*scale
    var sizeGapDp=0f//sizeGap*scale



    var leftSquare=0f//20f+sizeTileDp/2
    var rightSquare=0f//leftSquare+sizeSquareDp
    val topSquare=40f
    var bottomSquare=height-50f
    //val bottomSquare=width.coerceAtMost(height)/2f

     var remainderTileStartSquare=0
     var partTileStartSquare=0

    var remainderTileEndtSquare=0
    var partTileEndSquare=0

    var rightTile=0f//20f+sizeTileDp
    var topTile=70f
    var bottomTile=height-70f
    private val topMarkerLineY=topTile-10
    private var bottomMarkerLineY=bottomTile+30
    //private  var markerLineX=0f
    private var positionMarker=0f
    private val atrib:Atrib=Atrib(sizeTile, sizeSquare, sizeGap, true, false, false )




    fun calcSize(atrib:Atrib){
sizeTile=atrib.sizeTile
        sizeSquare=atrib.sizeSquare
        sizeGap=atrib.sizeGap
amountTilesFl=sizeSquare/(sizeTile+sizeGap)+1
     amountTiles=amountTilesFl.roundToInt()
        sizeTileDpWithGap=(width-40)/amountTiles
        scale = sizeTileDpWithGap/(sizeTile+sizeGap)
        sizeTileDp=(sizeTile*scale)
        sizeSquareDp=(sizeSquare*scale)
        sizeGapDp=(sizeGap*scale)

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

            remainderTileStartSquare=0
            partTileStartSquare=sizeTile.roundToInt()

            partTileEndSquare=(sizeSquare%(sizeTile+sizeGap)).roundToInt()
            remainderTileEndtSquare=(sizeTile-partTileEndSquare).roundToInt()

            Log.v("a","drawView  sizeSquare/(sizeTile+sizeGap)= ${sizeSquare / (sizeTile + sizeGap)}" )
        }else if(atrib.alignmentCenter){
            leftSquare=(width-sizeSquareDp.toFloat())/2
            rightSquare=leftSquare+sizeSquareDp
            //leftTile=(sizeSquareDp/2)%(sizeTileDp+sizeGapDp).toFloat()+leftSquare-sizeTileDp-sizeGapDp/2 // added -leftSquare
            leftTile=((sizeSquare/2)%(sizeTile+sizeGap))*scale+leftSquare-sizeTileDp-sizeGapDp/2
            rightTile=leftTile+sizeTileDp
           // Log.v("a","drawView  leftTile= $leftTile" )

            partTileStartSquare=((sizeSquare/2)%(sizeTile+sizeGap)-sizeGap/2).roundToInt()
            remainderTileStartSquare=(sizeTile-partTileStartSquare).roundToInt()

            partTileEndSquare=partTileStartSquare
            remainderTileEndtSquare=remainderTileStartSquare
        }else if(atrib.alignmentMiddle){
            leftSquare=(width-sizeSquareDp)/2
            rightSquare=leftSquare+sizeSquareDp
            leftTile=(width/2)%(sizeTileDp+sizeGapDp)-sizeTileDp/2
            rightTile=leftTile+sizeTileDp

            partTileStartSquare=(((sizeSquare/2-(sizeTile+sizeGap)/2))%(sizeTile+sizeGap)).roundToInt()
            remainderTileStartSquare=(sizeTile-partTileStartSquare).roundToInt()

            partTileEndSquare=partTileStartSquare
            remainderTileEndtSquare=remainderTileStartSquare
        }
      //  markerLineX=rightTile


        positionMarker=rightTile+sizeGapDp
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

        //val atrib:Atrib= Atrib(250f, 1000f, 2f, true,false,false)
        atrib.sizeTile=250f
        atrib.sizeSquare=1000f
        atrib.sizeGap=2f
        atrib.start()


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
        paintMarkerLine.color=Color.GREEN
        paintMarkerLine.strokeWidth=5f

        paintMarkerText.style=Paint.Style.STROKE
        paintMarkerText.color=Color.WHITE
        paintMarkerText.strokeWidth=1f
        paintMarkerText.textSize= 25F


    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (scale<=0f){
           // val atrib=Atrib(sizeTile, sizeSquare, sizeGap,true,false, false  )
                    calcSize(atrib)
        }



for (i in 1..amountTiles){
    canvas.drawRoundRect (leftTile+sizeTileDp*(i-1)+sizeGapDp*(i-1),topTile,rightTile+sizeTileDp*(i-1)+sizeGapDp*(i-1),bottomTile,5f,5f, paintTails )
    canvas.drawRoundRect (leftTile+sizeTileDp*(i-1)+sizeGapDp*(i-1),topTile,rightTile+sizeTileDp*(i-1)+sizeGapDp*(i-1),bottomTile,5f,5f, paintTailsStroke )



}
        canvas.drawRoundRect (leftSquare,topSquare,rightSquare,bottomSquare,5f,5f, paintSquareBorder )
        canvas.drawLine(positionMarker, topMarkerLineY, positionMarker ,bottomMarkerLineY, paintMarkerLine)

        //canvas.drawText("${((positionMarker-leftSquare+sizeGapDp)/scale).roundToInt()}", positionMarker-70,height-20f,paintMarkerText)
        val textLeft=((positionMarker-leftSquare)/scale).roundToInt().toString()
        val positionTextLeft=positionMarker-textLeft.length*16
        canvas.drawText(textLeft, positionTextLeft,height-20f,paintMarkerText)

        val textRight=(sizeSquare-((positionMarker-leftSquare)/scale)).roundToInt().toString()
        canvas.drawText(textRight, positionMarker+10,height-20f,paintMarkerText)

        val textPartTileEnd=partTileEndSquare.toString()
        val positionTextPartTileEnd=rightSquare-textPartTileEnd.length*16

        canvas.drawText(partTileEndSquare.toString(), positionTextPartTileEnd,topSquare+25f,paintMarkerText)
        canvas.drawText(remainderTileEndtSquare.toString(), rightSquare+5,topSquare+25f,paintMarkerText)


        val textRemaindTileStart=remainderTileStartSquare.toString()
        val positionTextRemaindTileStart=leftSquare-textRemaindTileStart.length*16

        canvas.drawText(textRemaindTileStart, positionTextRemaindTileStart,topSquare+25f,paintMarkerText)
        canvas.drawText(partTileStartSquare.toString(), leftSquare+5,topSquare+25f,paintMarkerText)

        canvas.drawText(sizeSquare.toInt().toString(), width/2f,25f,paintMarkerText)


    }

    fun setAlignmentStart(){
       // val atrib= Atrib(sizeTile, sizeSquare, sizeGap, alignmentStart = true, alignmentCenter = false, alignmentMiddle = false  )
        atrib.start()
        calcSize(atrib)
        invalidate()
    }

    fun setAlignmentCenter(){
        //val atrib= Atrib(sizeTile, sizeSquare, sizeGap, alignmentStart = false, alignmentCenter = true, alignmentMiddle = false  )
        atrib.centre()
        calcSize(atrib)
        invalidate()
    }

    fun setAlignmentMiddle(){
        //val atrib= Atrib(sizeTile, sizeSquare, sizeGap, alignmentStart = false, alignmentCenter = false, alignmentMiddle = true  )
        atrib.middle()
        calcSize(atrib)
        invalidate()
    }

    fun changeTile(size:Float){
        //val atrib= Atrib(size, sizeSquare, sizeGap, alignmentStart = true, alignmentCenter = false, alignmentMiddle = false  )
        atrib.sizeTile=size
        calcSize(atrib)
        invalidate()
    }

    fun changeSquare(size:Float){
        //val atrib= Atrib(sizeTile, size, sizeGap, alignmentStart = true, alignmentCenter = false, alignmentMiddle = false  )
        atrib.sizeSquare=size
        calcSize(atrib)
        //sizeSquare=size

        invalidate()
    }
    fun changeGap(size:Float){
        //val atrib= Atrib(sizeTile, sizeSquare, size, alignmentStart = true, alignmentCenter = false, alignmentMiddle = false  )
        atrib.sizeGap=size
        calcSize(atrib)
        invalidate()
    }
    class Atrib(sizeTile:Float, sizeSquare:Float, sizeGap:Float, alignmentStart:Boolean, alignmentCenter:Boolean, alignmentMiddle:Boolean ) {
        var sizeTile:Float=sizeTile
        var sizeSquare:Float=sizeSquare
        var sizeGap:Float=sizeGap
        var alignmentStart:Boolean=alignmentStart
        var alignmentCenter:Boolean=alignmentCenter
        var alignmentMiddle:Boolean=alignmentMiddle
fun start(){
    alignmentStart=true
    alignmentMiddle=false
    alignmentCenter=false
    }

        fun centre(){
            alignmentStart=false
            alignmentMiddle=false
            alignmentCenter=true
        }

        fun middle(){
            alignmentStart=false
            alignmentMiddle=true
            alignmentCenter=false
        }

    }

    class NewService : Service() {
        override fun onBind(p0: Intent?): IBinder? {
            TODO("Not yet implemented")
        }


    }

}