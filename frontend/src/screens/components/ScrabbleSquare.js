import React, { useState } from "react";

const ScrabbleSquare = ({ i, j, char, onClick, setCharacter }) => {
  return (
    <div onClick={() => setCharacter("A")} className="square centered">
      {!char ? null : char}
    </div>
  );
};

export default ScrabbleSquare;
