import React, { useState, useEffect } from "react";
import axios from "axios";
import ScrabbleBoard from "./components/ScrabbleBoard";
import ScrabbleHand from "./components/ScrabbleHand";
import Player from "../classes/Player";
import LetterBank from "../classes/LetterBank";

export default function StartScreen() {
  const [board, setBoard] = useState(null);
  const [oldBoard, setOldBoard] = useState(null);
  const [character, setCharacter] = useState("");
  const [hand, setHand] = useState([]);
  const [turn, setTurn] = useState(0);
  const [trade, setTrade] = useState(false);
  const [players, setPlayers] = useState([new Player([]), new Player([])]);
  const [tradedLetters, setTradedLetters] = useState([]);
  const [letterBank, setLetterBank] = useState(new LetterBank());

  useEffect(() => {
    axios.defaults.headers.post["Content-Type"] = "application/json";
    axios.defaults.headers.post["Accept"] = "application/json";
    const tempBoard = [];
    for (let i = 0; i < 15; ++i) {
      const tempRow = [];
      for (let j = 0; j < 15; ++j) {
        tempRow.push("");
      }
      tempBoard.push(tempRow);
    }
    setBoard(tempBoard);
    setOldBoard(tempBoard);
    givePlayersCard();
  }, []);

  const getLetters = async (player) => {
    console.log(tradedLetters);
    return axios
      .post("http://localhost:8080/letters/", {
        letterBank: letterBank.getAllLetters(),
        tradedLetters: tradedLetters,
        playerLetters: hand,
      })
      .then((response) => {
        letterBank.setAllLetters(response.data.letterBank);
        console.log(player);
        player.setHand(response.data.playerLetters);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const nextTurn = async () => {
    //   if (trade) {

    //   }
    let success = await authenticateBoard();
    if (!success) {
      console.log("here");
      return;
    }
    await getLetters(players[turn]);
    if (turn === players.length - 1) {
      setHand(players[0].getHand());
      setTurn(0);
    } else {
      setHand(players[turn + 1].getHand());
      setTurn(turn + 1);
    }
    setOldBoard(board);
    setTrade(false);
    setTradedLetters([]);
  };

  const givePlayersCard = async () => {
    for (let i = 0; i < players.length; ++i) {
      await getLetters(players[i]);
    }
    setHand(players[0].getHand());
  };

  const cancelTrade = () => {
    setTrade(false);
    setHand([...hand, ...tradedLetters]);
    setTradedLetters([]);
    setCharacter(null);
  };

  const startTrade = () => {
    setTrade(true);
    setHand(players[turn].getHand());
    setBoard(oldBoard);
    setCharacter(null);
  };

  const authenticateBoard = async () => {
    console.log(oldBoard);
    console.log(board);
    return axios
      .post("http://localhost:8080/authenticate/", {
        oldBoard: oldBoard,
        newBoard: board,
      })
      .then((response) => {
        console.log(response.data.success);
        return response.data.success;
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className="start flex-container">
      <div className="flex-row-container">
        <ScrabbleBoard
          character={character}
          setCharacter={setCharacter}
          board={board}
          setBoard={setBoard}
          trade={trade}
        />
        <div className="btn-container flex-container">
          <button onClick={() => nextTurn()}>End Turn</button>
          {trade ? (
            <button onClick={() => cancelTrade()}>Cancel Trade</button>
          ) : (
            <button onClick={() => startTrade()}>Start Trade</button>
          )}
        </div>
      </div>
      <ScrabbleHand
        character={character}
        setCharacter={setCharacter}
        hand={hand}
        setHand={setHand}
        tradedLetters={tradedLetters}
        setTradedLetters={setTradedLetters}
        trade={trade}
      />
    </div>
  );
}
