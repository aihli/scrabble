import React, { useEffect, useState } from "react";

const ScrabbleHand = ({ character, hand, setHand, setCharacter }) => {
  const [scrabbleHand, setScrabbleHand] = useState(null);

  const removeCharacterFromHand = (char) => {
    const tempHand = [];
    let removed = false;
    for (let i = 0; i < hand.length; ++i) {
      if (!removed && hand[i] === char) {
        continue;
      } else {
        tempHand.push(hand[i]);
      }
    }
    setHand(tempHand);
  };

  const setUpHand = (tempHand) => {
    console.log(tempHand);
    const newHand = [];
    for (let char of tempHand) {
      console.log(char);
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

  useEffect(() => {
    const tempHand = [];
    for (let char of hand) {
      tempHand.push(char);
    }
    setUpHand(tempHand);
  }, [character, hand]);

  return <div className="row">{scrabbleHand}</div>;
};

export default ScrabbleHand;
