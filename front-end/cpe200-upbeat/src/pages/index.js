import React, { useRef } from "react";
import dynamic from "next/dynamic";
import ConstructionEditor from "../../components/ConstructionEditor";

const Board = dynamic(() => import("../../components/Board"), {
  ssr: false,
});

export default function Home() {
  return (
    <div>
      <Board></Board>
      <ConstructionEditor></ConstructionEditor>
    </div>
  );
}
