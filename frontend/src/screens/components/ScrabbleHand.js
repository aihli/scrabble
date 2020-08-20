import React, { useEffect, useState } from "react";

const ScrabbleHand = ({
  character,
  hand,
  setHand,
  setCharacter,
  tradedLetters,
  setTradedLetters,
  trade,
}) => {
  const [scrabbleHand, setScrabbleHand] = useState(null);

  useEffect(() => {
    const tempHand = [];
    for (let char of hand) {
      tempHand.push(char);
    }
    setUpHand(tempHand);
  }, [character, hand, trade]);

  const removeCharacterFromHand = (char) => {
    const tempHand = [];
    let removed = false;
    if (trade) {
      setTradedLetters([...tradedLetters, char]);
    }
    if (!trade && character) {
      tempHand.push(character);
    }
    for (let i = 0; i < hand.length; ++i) {
      if (!removed && hand[i] === char) {
        removed = true;
        continue;
      } else {
        tempHand.push(hand[i]);
      }
    }
    setHand(tempHand);
  };

  const setUpHand = (tempHand) => {
    const newHand = [];
    for (let char of tempHand) {
      newHand.push(
        <div
          className="hand-square centered"
          onClick={() => {
            console.log(char);
            setCharacter(char);
            removeCharacterFromHand(char);
          }}
        >
          {char}
        </div>
      );
    }
    setScrabbleHand(newHand);
  };

  return <div className="row">{scrabbleHand}</div>;
};

export default ScrabbleHand;
