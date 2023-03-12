import { Button, Modal } from "react-bootstrap";
import React, { useState } from "react";

export default function landing() {
  const [showNewGame, setShowNewGame] = useState(false);
  const [showJoinGame, setShowJoinGame] = useState(false);

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

  return (
    <>
      <div
        className="index-container bg"
        style={{
          backgroundImage: `url("bg.png")`,
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
        <Modal.Header closeButton>New game</Modal.Header>
        <Modal.Body>
          <form>
            <div class="form-group row">
              <label for="inputEmail3" class="col-sm-3 col-form-label">
                Player name
              </label>
              <div class="col-sm-8">
                <input
                  type="email"
                  class="form-control"
                  id="inputEmail3"
                  placeholder="Email"
                />
              </div>
            </div>
            <div class="form-group row">
              <label for="inputEmail3" class="col-sm-3 col-form-label">
                Room name
              </label>
              <div class="col-sm-8">
                <input
                  type="email"
                  class="form-control"
                  id="inputEmail3"
                  placeholder="Email"
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
                  placeholder="Password"
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
                    />
                    <label class="form-check-label" for="gridRadios3">
                      5
                    </label>
                  </div>
                </div>
              </div>
            </fieldset>
            <div class="form-group row">
              <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">
                  Create
                </button>
              </div>
            </div>
          </form>
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
        <Modal.Header closeButton>Join game</Modal.Header>
        <Modal.Body>
          <form>
            <div class="form-group row">
              <label for="inputEmail3" class="col-sm-3 col-form-label">
                Player name
              </label>
              <div class="col-sm-8">
                <input
                  type="email"
                  class="form-control"
                  id="inputEmail3"
                  placeholder="Email"
                />
              </div>
            </div>
            <div class="form-group row">
              <label for="inputEmail3" class="col-sm-3 col-form-label">
                Room name
              </label>
              <div class="col-sm-8">
                <input
                  type="email"
                  class="form-control"
                  id="inputEmail3"
                  placeholder="Email"
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
                  placeholder="Password"
                />
              </div>
            </div>
            <div class="form-group row">
              <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">
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
