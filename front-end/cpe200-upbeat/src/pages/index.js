import { Button, Modal } from "react-bootstrap";
import React, { useEffect, useState } from "react";
import uuid from "react-uuid";
import { useRouter } from "next/router";
import axios from "axios";

export default function landing() {
  const [showNewGame, setShowNewGame] = useState(false);
  const [showJoinGame, setShowJoinGame] = useState(false);

  const [playerID, setPlayerID] = useState(uuid());

  // Create Match Form
  const [playerName, setPlayerName] = useState("Ball");
  const [roomName, setRoomName] = useState("room1");
  const [password, setPassword] = useState("1234");
  const [maxPlayer, setMaxPlayer] = useState(2);

  useEffect(() => {
    if (!localStorage.getItem("id")) {
      localStorage.setItem("id", uuid());
    }
  }, []);

  const handleCloseNewGame = () => {
    setShowNewGame(false);
  };

  const handleShowNewGame = () => {
    setShowNewGame(true);
  };

  const handleCloseJoinGame = () => {
    setShowJoinGame(false);
  };

  const handleShowJoinGame = () => {
    setShowJoinGame(true);
  };

  const router = useRouter();

  const create = async () => {
    const res = await axios.post(
      "http://localhost:8080/match/create",
      {
        maxPlayer: maxPlayer,
        roomName: roomName,
        password: password,
        host: playerName,
      },
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    if (res.data.ok) {
      localStorage.setItem("playerID", res.data.playerID);
      localStorage.setItem("matchID", res.data.matchID);
    }
    return;
  };

  return (
    <>
      <div
        className="index-container bg"
        style={{
          backgroundImage: `url("bg2.PNG")`,
        }}
      >
        <div></div>
        <div className="menu-group">
          <div className="menu-btn-group">
            <button className="menu-btn" onClick={handleShowNewGame}>
              New game
            </button>
            <button className="menu-btn" onClick={handleShowJoinGame}>
              Join game
            </button>
          </div>
        </div>
      </div>
      <Modal
        show={showNewGame}
        onHide={handleCloseNewGame}
        backdrop="static"
        keyboard={false}
        centered
        dialogClassName="modal-70w"
      >
        <Modal.Header closeButton style={{ backgroundColor: "#853605" }}>
          New game
        </Modal.Header>
        <Modal.Body style={{ background: "#f3b46c" }}>
          <form>
            <div class="form-group row">
              <label for="inputUsername3" class="col-sm-3 col-form-label">
                Player name
              </label>
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  id="inputUsername3"
                  placeholder="Username..."
                  onChange={(e) => setPlayerName(e.target.value)}
                />
              </div>
            </div>
            <div class="form-group row">
              <label for="inputRoomName3" class="col-sm-3 col-form-label">
                Room name
              </label>
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  id="inputRoomName3"
                  placeholder="Room name..."
                  onChange={(e) => setRoomName(e.target.value)}
                />
              </div>
            </div>
            <div class="form-group row">
              <label for="inputPassword3" class="col-sm-3 col-form-label">
                Password
              </label>
              <div class="col-sm-8">
                <input
                  type="password"
                  class="form-control"
                  id="inputPassword3"
                  placeholder="6-8 Characters"
                  onChange={(e) => setPassword(e.target.value)}
                />
              </div>
            </div>
            <fieldset class="form-group">
              <div class="row">
                <legend class="col-form-label col-sm-3 pt-0">Max player</legend>
                <div class="col-sm-8">
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="gridRadios"
                      id="gridRadios1"
                      value="option1"
                      onChange={(e) => setMaxPlayer(parseInt(e.target.value))}
                      checked
                    />
                    <label class="form-check-label" for="gridRadios1">
                      2
                    </label>
                  </div>
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="gridRadios"
                      id="gridRadios2"
                      value="option2"
                      onChange={(e) => setMaxPlayer(parseInt(e.target.value))}
                    />
                    <label class="form-check-label" for="gridRadios2">
                      3
                    </label>
                  </div>
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="gridRadios"
                      id="gridRadios3"
                      value="option3"
                      onChange={(e) => setMaxPlayer(parseInt(e.target.value))}
                    />
                    <label class="form-check-label" for="gridRadios3">
                      4
                    </label>
                  </div>
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="gridRadios"
                      id="gridRadios3"
                      value="option3"
                      onChange={(e) => setMaxPlayer(parseInt(e.target.value))}
                    />
                    <label class="form-check-label" for="gridRadios3">
                      5
                    </label>
                  </div>
                </div>
              </div>
            </fieldset>
            <div class="form-group row">
              <div class="col-sm-10"></div>
            </div>
          </form>
          <button
            onClick={() => router.push("/game")}
            class="btn"
            style={{ background: "#ffd284", border: "2px solid #fa9305" }}
          >
            Create
          </button>
        </Modal.Body>
      </Modal>
      <Modal
        show={showJoinGame}
        onHide={handleCloseJoinGame}
        backdrop="static"
        keyboard={false}
        centered
        dialogClassName="modal-70w"
      >
        <Modal.Header closeButton style={{ background: "#853605" }}>
          Join game
        </Modal.Header>
        <Modal.Body style={{ background: "#f3b46c" }}>
          <form>
            <div class="form-group row">
              <label for="inputUsername3" class="col-sm-3 col-form-label">
                Player name
              </label>
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  id="inputUsername3"
                  placeholder="Username..."
                />
              </div>
            </div>
            <div class="form-group row">
              <label for="inputRoomName3" class="col-sm-3 col-form-label">
                Room name
              </label>
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  id="inputRoomName3"
                  placeholder="Room Name..."
                />
              </div>
            </div>
            <div class="form-group row">
              <label for="inputPassword3" class="col-sm-3 col-form-label">
                Password
              </label>
              <div class="col-sm-8">
                <input
                  type="password"
                  class="form-control"
                  id="inputPassword3"
                  placeholder="Password..."
                />
              </div>
            </div>
            <div class="form-group row">
              <div class="col-sm-10">
                <button
                  type="submit"
                  class="btn"
                  style={{ background: "#ffd284", border: "2px solid #fa9305" }}
                >
                  Join
                </button>
              </div>
            </div>
          </form>
        </Modal.Body>
      </Modal>
    </>
  );
}
