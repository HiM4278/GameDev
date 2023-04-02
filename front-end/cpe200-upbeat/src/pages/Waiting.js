import { useEffect, useState } from "react";
import { url } from "../../Lib/constant";
import { Client } from "@stomp/stompjs";
import { useRouter } from "next/router";
import { isPlaying } from "../../Lib/auth";

export default function Waiting() {
  const [host, setHost] = useState(true);
  const [isChecked, setIsChecked] = useState(false);
  const [numPlayer, setNum] = useState(0);

  const [client, setClient] = useState(null);

  const router = useRouter();

  useEffect(() => {
    isPlaying(router);
  }, []);

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

  const start = () => {
    if (client) {
      if (client.connected) {
        client.publish({
          destination: `/app/match/${localStorage.getItem("matchID")}/start`,
          body: JSON.stringify({}),
        });
      }
    }
  };

  const setState = (data) => {
    setHost(data.hostID);
    setNum(data.numPlayer);
    if (data.playing) {
      router.push("/game");
    }
  };

  return (
    <div>
      <div className="numPlay">{numPlayer}</div>
      <div className="wait">
        {localStorage.getItem("playerID") == host ? (
          <button
            className="wait-btn"
            disabled={numPlayer < 2}
            onClick={() => start()}
          >
            Start
          </button>
        ) : (
          <></>
        )}
      </div>
    </div>
  );
}
