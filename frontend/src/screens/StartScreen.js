import React, { useState, useEffect } from "react";
import axios from "axios";
import ScrabbleBoard from "./components/ScrabbleBoard";
import ScrabbleHand from "./components/ScrabbleHand";
import Player from "../classes/Player";
import LetterBank from "../classes/LetterBank";

export default function StartScreen() {
  const [board, setBoard] = useState(null);
  const [character, setCharacter] = useState("");
  const [hand, setHand] = useState([]);
  const [turn, setTurn] = useState(0);
  const [players, setPlayers] = useState([new Player([]), new Player([])]);
  const [letterBank, setLetterBank] = useState(new LetterBank());

  useEffect(() => {
    const tempBoard = [];
    for (let i = 0; i < 15; ++i) {
      const tempRow = [];
      for (let j = 0; j < 15; ++j) {
        tempRow.push(null);
      }
      tempBoard.push(tempRow);
    }
    setBoard(tempBoard);
    getLetters(players[0]);
  }, []);

  const getLetters = (player) => {
    axios.defaults.headers.post["Content-Type"] = "application/json";
    axios.defaults.headers.post["Accept"] = "application/json";
    axios
      .post("http://localhost:8080/letters/", {
        letterBank: letterBank.getAllLetters(),
        tradedLetters: [],
        playerLetters: player.getHand(),
      })
      .then((response) => {
        letterBank.setAllLetters(response.data.letterBank);
        console.log(player);
        player.setHand(response.data.playerLetters);
        setHand(player.getHand());
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const nextTurn = () => {
    if (turn === players.length - 1) {
      setTurn(0);
      getLetters(players[0]);
    } else {
      setTurn(turn + 1);
      getLetters(players[turn + 1]);
    }
  };

  return (
    <div className="start flex-container">
      <div className="flex-row-container">
        <ScrabbleBoard
          character={character}
          setCharacter={setCharacter}
          board={board}
          setBoard={setBoard}
        />
        <div className="btn-container flex-container">
          <button onClick={() => nextTurn()}>End Turn</button>
          <button>Trade Hand</button>
        </div>
      </div>
      <ScrabbleHand
        character={character}
        setCharacter={setCharacter}
        hand={hand}
        setHand={setHand}
      />
    </div>
  );
}
