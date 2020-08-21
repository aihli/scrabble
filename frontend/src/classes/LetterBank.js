class LetterBank {
  constructor() {
    this.letters = new Map();
    this.letters.set("A", 9);
    this.letters.set("B", 2);
    this.letters.set("C", 2);
    this.letters.set("D", 4);
    this.letters.set("E", 12);
    this.letters.set("F", 2);
    this.letters.set("G", 3);
    this.letters.set("H", 2);
    this.letters.set("I", 9);
    this.letters.set("J", 1);
    this.letters.set("K", 1);
    this.letters.set("L", 4);
    this.letters.set("M", 2);
    this.letters.set("N", 6);
    this.letters.set("O", 8);
    this.letters.set("P", 2);
    this.letters.set("Q", 1);
    this.letters.set("R", 6);
    this.letters.set("S", 4);
    this.letters.set("T", 6);
    this.letters.set("U", 4);
    this.letters.set("V", 2);
    this.letters.set("W", 2);
    this.letters.set("X", 1);
    this.letters.set("Y", 2);
    this.letters.set("Z", 1);
  }

  getAllLetters() {
    let list = [];
    for (let i of this.letters.keys()) {
      for (let j = 0; j < this.letters.get(i); ++j) {
        list.push(i);
      }
    }
    return list;
  }

  setAllLetters(list) {
    this.letters = new Map();
    this.letters.set("A", 0);
    this.letters.set("B", 0);
    this.letters.set("C", 0);
    this.letters.set("D", 0);
    this.letters.set("E", 0);
    this.letters.set("F", 0);
    this.letters.set("G", 0);
    this.letters.set("H", 0);
    this.letters.set("I", 0);
    this.letters.set("J", 0);
    this.letters.set("K", 0);
    this.letters.set("L", 0);
    this.letters.set("M", 0);
    this.letters.set("N", 0);
    this.letters.set("O", 0);
    this.letters.set("P", 0);
    this.letters.set("Q", 0);
    this.letters.set("R", 0);
    this.letters.set("S", 0);
    this.letters.set("T", 0);
    this.letters.set("U", 0);
    this.letters.set("V", 0);
    this.letters.set("W", 0);
    this.letters.set("X", 0);
    this.letters.set("Y", 0);
    this.letters.set("Z", 0);
    for (let letter of list) {
      this.letters.set(letter, 1 + this.letters.get(letter));
    }
  }
}

export default LetterBank;
