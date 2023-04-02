import { useEffect, useState } from "react";
import { url } from "../../Lib/constant";
import { Client } from "@stomp/stompjs";
import { useRouter } from "next/router";

export default function Waiting() {
  const [host, setHost] = useState(true);
  const [isChecked, setIsChecked] = useState(false);
  const [numPlayer, setNum] = useState(0);

  const [client, setClient] = useState(null);

  const router = useRouter();

  useEffect(() => {
    if (!client) {
      const client = new Client({
        brokerURL: `ws://${url}/upbeat-websocket`,
        onConnect: () => {
          client.subscribe(
            `/topic/match/${localStorage.getItem("matchID")}`,
            (message) => {
              const body = JSON.parse(message.body);
              setState(body);
            }
          );
          client.subscribe(
            `/app/match/${localStorage.getItem("matchID")}`,
            (message) => {
              const body = JSON.parse(message.body);
              setState(body);
            }
          );
        },
      });
      client.activate();
      setClient(client);
    }
  }, []);

  const setState = (data) => {
    setHost(data.hostID);
    setNum(data.numPlayer);
  };

  return (
    <div
      className="index-container bg"
      style={{
        backgroundImage: `url("bgWaiting.png")`,
      }}
    >
      <div className="menu-group">
        <div className="menu-btn-group">
          <div
            className="numPlay"
            style={{
              color: "#eadfce",
              display: "flex",
              flexDirection: "column",
            }}
          >
            {/* <padding style={{ textAlign: "center" }}>Total player: </padding> */}
            {numPlayer}
          </div>
          <div style={{ position: "absolute", top: "70%", left: "44%" }}>
            {host ? (
              <button
                className="wait-btn"
                disabled={numPlayer < 2}
                onClick={() => router.push("/game")}
              >
                Start
              </button>
            ) : (
              false
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
