package com.example.tictaktoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity(), View.OnClickListener {

    var Player = true
    var turncount=0
    val boardStatus = Array(3){IntArray(3)}//3x3
    lateinit var board :Array<Array<Button>> //2D array
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for(i: Array<Button> in board) {
            for (button: Button in i) {
                button.setOnClickListener(this)

            }
        }
        initializeBoardStatus()
        resetBtn.setOnClickListener {

            Player =true
            turncount=0
            initializeBoardStatus()


        }

    }
    private fun  initializeBoardStatus()
    {
        for(i:Int in 0..2) {
            for (j: Int in 0..2) {
                boardStatus[i][j] = -1
            }
        }
        for(i: Array<Button> in board) {
            for (button: Button in i) {
                button.isEnabled=true
                button.text=""

            }
        }

    }

   override fun onClick(view: View)
    {
        when(view.id)
        {
            R.id.button1->
            {
                updateValue(row=0,col=0,player= Player)
            }

            R.id.button2->
            {
                updateValue(row=0,col=1,player= Player)
            }

            R.id.button3->
            {
                updateValue(row=0,col=2,player= Player)
            }

            R.id.button4->
            {
                updateValue(row=1,col=0,player= Player)
            }

            R.id.button5->
            {
                updateValue(row=1,col=1,player= Player)
            }

            R.id.button6->
            {
                updateValue(row=1,col=2,player= Player)
            }

            R.id.button7->
            {
                updateValue(row=2,col=0,player= Player)
            }

            R.id.button8->
            {
                updateValue(row=2,col=1,player= Player)
            }

            R.id.button9->
            {
                updateValue(row=2,col=2,player= Player)
            }
        }
        turncount++
        Player =!Player
        if(Player)
        {
            updateDisplay("Player X Turn")
        }
        else
        {
            updateDisplay("Player O Turn")
        }

        if (turncount==9)
        {
            updateDisplay("Game Draw")
        }
        checkwinner()
    }

    private fun checkwinner() {
        //horizontal rows
        for (i: Int in 0..2) {

            if ((boardStatus[i][0] == boardStatus[i][1]) && boardStatus[i][0]== boardStatus[i][2]) {
                if(boardStatus[i][0]==1)
                {
                    updateDisplay("Player X Winner")
                    break
                }
                else if(boardStatus[i][0]==0)
                {
                    updateDisplay("Player O Winner")
                    break
                }


            }
        }
        //Vertical Columns
        for (i: Int in 0..2) {

            if ((boardStatus[0][i] == boardStatus[1][i]) && boardStatus[0][i]== boardStatus[2][i]) {
                if(boardStatus[0][i]==1)
                {
                    updateDisplay("Player X Winner")
                    break
                }
                else if(boardStatus[0][i]==0)
                {
                    updateDisplay("Player O Winner")
                    break
                }


            }
        }

        //first diagnol
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] ==  boardStatus[2][2])
        {
            if(boardStatus[0][0]==1)
            {
                updateDisplay("Player X Winner")

            }
            else if(boardStatus[0][0]==0)
            {
                updateDisplay("Player O Winner")

            }


        }
        //second diagonal
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] ==  boardStatus[2][0])
        {
            if(boardStatus[0][2]==1)
            {
                updateDisplay("Player X Winner")

            }
            else if(boardStatus[0][2]==0)
            {
                updateDisplay("Player O Winner")

            }


        }

    }

    private fun updateDisplay(text: String) {

            displayTv.text = text
            if(text.contains("Winner"))
            {
                disable()
            }
    }

    private fun disable()
    {
        for(i: Array<Button> in board) {
            for (button: Button in i) {
                button.isEnabled=false

            }
        }

    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {

        val text:String = if(player) "X" else "O"
        val value:Int = if (player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }

        boardStatus[row][col]= value

    }

}

