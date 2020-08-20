class Player {
  constructor(hand) {
    this.hand = hand;
    this.getHand.bind(this);
    this.setHand.bind(this);
  }
  getHand() {
    return this.hand;
  }
  setHand(hand) {
    console.log(this.hand);
    this.hand = hand;
    console.log(this.hand);
  }
}

export default Player;
