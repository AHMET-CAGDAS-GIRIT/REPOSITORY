#include <stdio.h>
#include <cstdlib>

bool checker(int board[9][9],bool isItOriginal[9][9],int rowNumber ,int columnNumber ,int inputValue){
    if(isItOriginal[rowNumber][columnNumber]){
        return false;
    }
    for(int i = 0; i < 9 ; i++){
        if(board[i][columnNumber] == inputValue){
            return false;
        }
    }
    for(int i = 0; i < 9 ; i++){
        if(board[rowNumber][i] == inputValue){
            return false;
        }
    }
    for(int i = rowNumber - rowNumber%3; i < rowNumber - rowNumber%3 + 3; i++){
        for(int j = columnNumber -columnNumber%3; j < columnNumber - columnNumber%3 + 3 ; j++){
            if(board[i][j] == inputValue){
                return false;
            }
        }
    }
    return true;
}

void boardCeator(int board[9][9], bool isItOriginal[9][9],int &numberOfEmptyElements){
    int difficulty;
    printf("Please enter the difficulty level\n");
    wrongInput:
    scanf("%d",&difficulty);
    if(difficulty > 50 || difficulty < 1){
        printf("The difficulty level must not be higher than 50 or lower than 1\n");
        goto wrongInput;
    }
    numberOfEmptyElements = difficulty;
    startFromScratch:
    int rowShuffle[9];
    int columnShuffle[9];
    int numberShuffle[9];
    //filling the shufflers
    for(int i = 0; i < 9 ; i++){
        rowShuffle[i] = i;
        columnShuffle[i] = i;
        numberShuffle[i] = i;
    }
    for(int i = 0; i < 9 ; i++){
        int tempStorage;
        int tempDisplacementAmount;
        tempDisplacementAmount = rand()%2 + 1;
        tempStorage = rowShuffle[i];
        rowShuffle[i] = rowShuffle[(i + tempDisplacementAmount )%3 + (i - i%3)];
        rowShuffle[(i + tempDisplacementAmount )%3 + (i - i%3)] = tempStorage;
        tempDisplacementAmount = rand()%2 + 1;
        tempStorage = columnShuffle[i];
        columnShuffle[i] = columnShuffle[(i + tempDisplacementAmount )%3 + (i - i%3)];
        columnShuffle[(i + tempDisplacementAmount )%3 + (i - i%3)] = tempStorage;
        tempDisplacementAmount = rand()%8 + 1;
        tempStorage = numberShuffle[i];
        numberShuffle[i] = numberShuffle[(i + tempDisplacementAmount )%9];
        numberShuffle[(i + tempDisplacementAmount )%9] = tempStorage;
    }
    //the end of "filling the shufflers"
    //note: the shufflers aren't fully random.They still have some rules.(rowShuffle[i]/3 = i/3 and columnShuffle[i]/3 = i/3)
    //board filler
    for(int i = 0; i < 9 ; i++){
        for(int j = 0; j < 9 ; j++){
            board[i][j] = numberShuffle[(rowShuffle[i]*3 + i/3  + columnShuffle[j] )%9] + 1;
        }
    }
    //the end of "board filler"
    //board eraser
    int numberOfTrialsToRemoveAnElement = 0;
    for(int i = 0; i < difficulty ; i++){
        if(numberOfTrialsToRemoveAnElement > 100){
            goto startFromScratch;
        }
        int randomEraseRow;
        int randomEraseColumn;
        int tempElementStorageForTesting;
        wasEmptyTryAgain:
        randomEraseRow = rand()%9;
        randomEraseColumn = rand()%9;
        if(board[randomEraseRow][randomEraseColumn] == 0){
            goto wasEmptyTryAgain;
        }
        tempElementStorageForTesting = board[randomEraseRow][randomEraseColumn];
        board[randomEraseRow][randomEraseColumn] = 0;
        isItOriginal[randomEraseRow][randomEraseColumn] = false;
        bool wasAnElementSuccessfullyRemoved = true;
        for(int j = 0; j < 9 ; j++){
            if(j != tempElementStorageForTesting){
                if(checker(board,isItOriginal,randomEraseRow,randomEraseColumn,j)){
                    numberOfTrialsToRemoveAnElement++;
                    wasAnElementSuccessfullyRemoved = false;
                    board[randomEraseRow][randomEraseColumn] = tempElementStorageForTesting;
                    isItOriginal[randomEraseRow][randomEraseColumn] = true;
                    i--;
                    break;
                }
            }
        }
        if(wasAnElementSuccessfullyRemoved){
            numberOfTrialsToRemoveAnElement = 0;
        }
    }
    //the end of "board eraser"
}


void boardPrinter(int board[9][9]){
    for(int i = 0; i < 9 ; i++){
        for(int j = 0; j < 9 ; j++){
            if(board[i][j] != 0){
                printf("%d ",board[i][j]);
            }else{
                printf("_ ");
            }
            if(j%3 == 2 && j != 8){
                printf("| ");
            }
        }
        printf("\n");
        if(i%3 == 2 && i != 8){
            printf("----------------------\n");
        }
    }
}
int main(){
    //creating the board
    int board[9][9];
    bool isItOriginal[9][9];
    int numberOfEmptyElements;
    for(int i = 0; i < 9 ; i++){
        for(int j = 0; j < 9 ; j++){
            isItOriginal[i][j] = true;
        }
    }
    boardCeator(board,isItOriginal,numberOfEmptyElements);
    //the end of "creating the board"
    boardPrinter(board);
    int rowNumber;
    int columnNumber;
    int inputValue;
    while(true){
        wrongInput:
        printf("please enter row number\n");
        scanf("%d",&rowNumber);
        printf("please enter column number\n");
        scanf("%d",&columnNumber);
        printf("please enter input value\n");
        scanf("%d",&inputValue);
        if(rowNumber < 1 || rowNumber > 9 || columnNumber < 1 || columnNumber > 9 || inputValue < 1 || inputValue > 9){
            printf("please make sure all the inputs you give are between 1 and 9\n\n\n");
            goto wrongInput;
        }
        if(checker(board,isItOriginal,rowNumber - 1 ,columnNumber - 1,inputValue)){
            board[rowNumber-1][columnNumber-1] = inputValue;
            numberOfEmptyElements--;
        }else{
            printf("this value cannot be put\n");
        }
        boardPrinter(board);
        if(numberOfEmptyElements == 0){
            break;
        }
    }
    printf("You won the game.Give a number to exit\n");
    scanf("%d",&inputValue);
}

