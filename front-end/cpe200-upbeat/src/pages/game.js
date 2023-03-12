import React, { useRef } from "react";
import dynamic from "next/dynamic";
import ConstructionEditor from "../../components/ConstructionEditor";

const Board = dynamic(() => import("../../components/Board"), {
  ssr: false,
});

export default function Home() {
  const containerRef = useRef(null);
  return (
    <div className="game-container">
      <ConstructionEditor></ConstructionEditor>
      <div className="board-container" ref={containerRef}>
        <Board container={containerRef}></Board>
      </div>
    </div>
  );
}
