import React, { useState, useEffect } from "react";
import ScrabbleHand from "./ScrabbleHand";

const ScrabbleBoard = ({ character, setCharacter, board, setBoard }) => {
  const [scrabbleBoard, setScrabbleBoard] = useState(null);

  const setUpBoard = (tempBoard) => {
    const newBoard = [];
    for (let i = 0; i < 15; ++i) {
      const newRow = [];
      for (let j = 0; j < 15; ++j) {
        newRow.push(
          <div
            key={j}
            className="square centered"
            i={i}
            j={j}
            onClick={() => handleChangeBoard(i, j)}
          >
            {tempBoard && tempBoard[i][j] ? tempBoard[i][j] : null}
          </div>
        );
      }
      newBoard.push(
        <div key={i} className="row">
          {newRow}
        </div>
      );
    }
    setScrabbleBoard(newBoard);
  };

  const handleChangeBoard = (i, j) => {
    console.log(character);
    if (character) {
      const tempBoard = [];
      for (let i = 0; i < 15; ++i) {
        const tempRow = [];
        for (let j = 0; j < 15; ++j) {
          tempRow.push(board[i][j]);
        }
        tempBoard.push(tempRow);
      }
      tempBoard[i][j] = character;
      setBoard(tempBoard);
      setCharacter(null);
    }
  };

  useEffect(() => {
    setUpBoard(board);
  }, [character, board]);

  return <div>{scrabbleBoard}</div>;
};

export default ScrabbleBoard;
