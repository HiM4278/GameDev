import { Button, Modal } from "react-bootstrap";
import React, { useEffect, useState } from "react";
import uuid from "react-uuid";
import { useRouter } from "next/router";
import axios from "axios";
import { url } from "../../Lib/constant";

export default function landing() {
  const [showNewGame, setShowNewGame] = useState(false);
  const [showJoinGame, setShowJoinGame] = useState(false);

  // Create Match Form
  const [playerName, setPlayerName] = useState("");
  const [roomName, setRoomName] = useState("");
  const [password, setPassword] = useState("");
  const [maxPlayer, setMaxPlayer] = useState(2);

  useEffect(() => {
    isPlaying();
  }, []);

  const clearForm = () => {
    setPlayerName("");
    setRoomName("");
    setPassword("");
    setMaxPlayer(2);
  };

  const handleCloseNewGame = () => {
    setShowNewGame(false);
  };

  const handleShowNewGame = () => {
    setShowNewGame(true);
    clearForm();
  };

  const handleCloseJoinGame = () => {
    setShowJoinGame(false);
  };

  const handleShowJoinGame = () => {
    setShowJoinGame(true);
    clearForm();
  };

  const router = useRouter();

  const create = async () => {
    const res = await axios.post(
      `http://${url}/match/create`,
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
      router.push("/Waiting");
    }
    return;
  };

  const join = async () => {
    const res = await axios.put(
      `http://${url}/match/join`,
      {
        roomName: roomName,
        password: password,
        playerName: playerName,
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
      router.push("/Waiting");
    }
    return;
  };

  const check = async (playerID, matchID) => {
    const res = await axios.get(
      `http://${url}/match/check?playerID=${playerID}&matchID=${matchID}`
    );
    if (res.data.ok) {
      router.push("/Waiting");
    } else {
      localStorage.removeItem("playerID");
      localStorage.removeItem("matchID");
    }
    return;
  };

  const isPlaying = () => {
    if (localStorage.getItem("playerID") && localStorage.getItem("matchID")) {
      check(localStorage.getItem("playerID"), localStorage.getItem("matchID"));
    } else {
      localStorage.removeItem("playerID");
      localStorage.removeItem("matchID");
    }
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
        <Modal.Header
          closeButton
          style={{
            backgroundColor: "#915e25",
            width: "600px",
            fontSize: "20px",
            color: "white",
            fontWeight: "bold",
            fontFamily: "Tilt Neon",
            fontWeight: "bolder",
            textTransform: "uppercase",
          }}
        >
          New game
        </Modal.Header>
        <Modal.Body
          style={{ background: "#c9a95e", width: "600px", height: "400px" }}
        >
          <form>
            <div
              class="form-group row"
              style={{ alignItems: "center", justifyContent: "center" }}
            >
              <label for="inputUsername3" class="col-sm-3 col-form-label">
                Player name:
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
            <div
              class="form-group row"
              style={{ alignItems: "center", justifyContent: "center" }}
            >
              <label for="inputRoomName3" class="col-sm-3 col-form-label">
                Room name:
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
            <div
              class="form-group row"
              style={{ alignItems: "center", justifyContent: "center" }}
            >
              <label for="inputPassword3" class="col-sm-3 col-form-label">
                Password:
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
              <div
                class="row"
                style={{ alignItems: "center", justifyContent: "center" }}
              >
                <legend class="col-form-label col-sm-3 pt-0">
                  Max player:
                </legend>
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
          <div style={{ position: "absolute", bottom: 15 }}>
            <button
              onClick={() => create()}
              class="btn"
              style={{
                background: "#ffd284",
                border: "3.5px solid #a2580c",
                width: "100px",
                height: "50px",
                fontSize: "20px",
                color: "#a05605",
                fontFamily: "Tilt Neon",
                fontWeight: "bolder",
                textTransform: "uppercase",
              }}
            >
              Create
            </button>
          </div>
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
        <Modal.Header
          closeButton
          style={{
            background: "#915e25",
            width: "600px",
            fontSize: "20px",
            color: "white",
            fontWeight: "bold",
            fontFamily: "Tilt Neon",
            fontWeight: "bolder",
            textTransform: "uppercase",
          }}
        >
          Join game
        </Modal.Header>
        <Modal.Body
          style={{ background: "#c9a95e", width: "600px", height: "400px" }}
        >
          <div>
            <form>
              <div
                class="form-group row"
                style={{ alignItems: "center", justifyContent: "center" }}
              >
                <label for="inputUsername3" class="col-sm-3 col-form-label">
                  Player name:
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
              <div
                class="form-group row"
                style={{ alignItems: "center", justifyContent: "center" }}
              >
                <label for="inputRoomName3" class="col-sm-3 col-form-label">
                  Room name:
                </label>
                <div class="col-sm-8">
                  <input
                    type="text"
                    class="form-control"
                    id="inputRoomName3"
                    placeholder="Room Name..."
                    onChange={(e) => setRoomName(e.target.value)}
                  />
                </div>
              </div>
              <div
                class="form-group row"
                style={{ alignItems: "center", justifyContent: "center" }}
              >
                <label for="inputPassword3" class="col-sm-3 col-form-label">
                  Password:
                </label>
                <div class="col-sm-8">
                  <input
                    type="password"
                    class="form-control"
                    id="inputPassword3"
                    placeholder="Password..."
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
              </div>
            </form>
          </div>
          <div style={{ position: "absolute", bottom: 15 }}>
            <button
              type="submit"
              class="btn"
              style={{
                background: "#ffd284",
                border: "3.5px solid #a2580c",
                width: "100px",
                height: "50px",
                fontSize: "20px",
                color: "#a05605",
                fontFamily: "Tilt Neon",
                fontWeight: "bolder",
                textTransform: "uppercase",
              }}
              onClick={() => join()}
            >
              Join
            </button>
          </div>
        </Modal.Body>
      </Modal>
    </>
  );
}
