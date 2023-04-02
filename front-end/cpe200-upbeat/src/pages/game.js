import React, { useEffect, useRef, useState } from "react";
import dynamic from "next/dynamic";
import ConstructionEditor from "../../components/ConstructionEditor";
import { Client } from "@stomp/stompjs";
import { url } from "../../Lib/constant";

const Board = dynamic(() => import("../../components/Board"), {
  ssr: false,
});

export default function Home() {
  const [client, setClient] = useState(null);

  useEffect(() => {
    if (!client) {
      const client = new Client({
        brokerURL: `ws://${url}/upbeat-websocket`,
        onConnect: () => {
          client.subscribe(
            `/topic/game/map/${localStorage.getItem("matchID")}`,
            (message) => {
              const body = JSON.parse(message.body);
              console.log(body);
            }
          );
          client.subscribe(
            `/app/game/map/${localStorage.getItem("matchID")}`,
            (message) => {
              const body = JSON.parse(message.body);
              console.log(body);
            }
          );
        },
      });
      client.activate();
      setClient(client);
    }
  }, []);

  const containerRef = useRef(null);
  return (
    <div className="game-container" ref={containerRef}>
      <ConstructionEditor></ConstructionEditor>
      <div className="board-container">
        <Board container={containerRef}></Board>
      </div>
    </div>
  );
}
