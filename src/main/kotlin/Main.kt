var ticketPrice = 0
var half = 0

var numberOfRows = 1
var numberOfSeats = 1
var seatsIndex = 0

var availableSeat = 'S'
var mainList = mutableListOf<MutableList<Char>>()
var listOfSeatsInTheRow = mutableListOf<Char>()

var ticketRow = 0
var ticketSeat = 0

var purchasedTickets = 0
var totalIncome = 0
var firstHalfMoney = 0
var secondHalfMoney = 0

var currentIncome = 0

var oneSeatPercentage = 0.0

class CreateHall {

    fun rowsAndSeats() {
        println("Enter the number of rows:")
        val temp1 = readln().toInt()
        numberOfRows = temp1

        println("Enter the number of seats in each row:")
        val temp2 = readln().toInt()
        numberOfSeats = temp2
    }
}

class PrintFirstLine {
    fun printFirstLine() {
        println("Cinema:")
        print(" ")
        for (i in 1..numberOfSeats) {

            print(" $i")

            seatsIndex++
        }
        println()
    }
}

class CreateMain {

    fun createRestPartOfHall() {

        for (i in 1..numberOfSeats) {
            listOfSeatsInTheRow.add(availableSeat)
        }

        for (i in 1..numberOfRows) {
            mainList.addAll(listOf(listOfSeatsInTheRow.toMutableList()))
        }
    }

    fun buyTicket() {

        do {
            println("Enter a row number:")
            val temp1 = readln().toInt()
            ticketRow = temp1

            println("Enter a seat number in that row:")
            val temp2 = readln().toInt()
            ticketSeat = temp2

            if (ticketRow > numberOfRows || ticketSeat > numberOfSeats){
                ticketRow = 1
                ticketSeat = 1
                println("Wrong input!")
                println()
                continue
            }

            if (mainList[ticketRow - 1][ticketSeat - 1] == 'B') {
                println("That ticket has already been purchased!")
                println()
            }
        } while (mainList[ticketRow - 1][ticketSeat - 1] != 'S')
    }

    fun changeList() {

        mainList[ticketRow - 1][ticketSeat - 1] = 'B'
        purchasedTickets++
        oneSeatPercentage = purchasedTickets.toDouble() / (numberOfRows * numberOfSeats).toDouble() * 100.0
    }
}

class PrintState {
    fun printState() {
        val newList = mainList.toMutableList()
        for ((newListIndex, i) in (1..numberOfRows).withIndex()) {
            println("${newListIndex + 1} ${newList[newListIndex].joinToString().replace(",", "")}")
        }
        println()
    }
}

class TicketPrice {
    var totalSeats = numberOfSeats * numberOfRows

    fun calculatePrice() {
        half = numberOfRows / 2

        if (totalSeats <= 60) {
            ticketPrice = 10
            currentIncome += 10
        }
        if (totalSeats > 60) {
            if (ticketRow <= half) {
                ticketPrice = 10
                currentIncome += 10
            } else {
                ticketPrice = 8
                currentIncome += 8
            }
        }

        println()
        println("Ticket price: $$ticketPrice")
        println()
    }
}

class Menu {
    fun printMenu() {
        println(
            "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit"
        )
    }
}

class Statistics {

    fun totalIncomeFromHall() {
        println()
        val totalSeats = numberOfSeats * numberOfRows
        half = numberOfRows / 2

        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10
        }
        if (totalSeats > 60) {
            firstHalfMoney = half * numberOfSeats * 10
            secondHalfMoney = (numberOfRows - half) * numberOfSeats * 8
            totalIncome = firstHalfMoney + secondHalfMoney
        }
    }

    fun printStatistics() {
        println("Number of purchased tickets: $purchasedTickets")
        println("Percentage: ${("%.2f".format(oneSeatPercentage))}%")
        println("Current income: $$currentIncome")
        println("Total income: $$totalIncome")
        println()
    }
}

fun main() {

    val yourChoice = CreateHall()
    yourChoice.rowsAndSeats()

    val forCreation = CreateMain()
    forCreation.createRestPartOfHall()

    val ourMenu = Menu()

    val firstPrint = PrintFirstLine()

    val currentState = PrintState()

    val priceYourTicket = TicketPrice()

    val cinemaStatistics = Statistics()

    do {
        ourMenu.printMenu()
        val yourChoice = readln().toInt()

        if (yourChoice == 1) {
            firstPrint.printFirstLine()
            currentState.printState()
        }
        if (yourChoice == 2) {
            forCreation.buyTicket()
            forCreation.changeList()
            priceYourTicket.calculatePrice()
        }
        if (yourChoice == 3) {
            cinemaStatistics.totalIncomeFromHall()
            cinemaStatistics.printStatistics()

        } else {
            continue
        }
    } while (yourChoice != 0)
}