import React, { useEffect, useRef, useState } from "react";
import { Circle, Layer, Stage, Image } from "react-konva";
import { createTerritory } from "../Lib/createTerritory";

function Board(props) {
  const [firstRender, setFirstRender] = useState(true);
  const [territory, setTerritory] = useState([]);
  const [images, setImages] = useState([]);
  const stageRef = useRef(null);
  const layerRef = useRef(null);
  function handleCircleMouseOver(i, setTerritory, territory) {
    const newTerritory = [...territory];
    newTerritory[i].opacity = 0.5; // update opacity of Image component
    setTerritory(newTerritory); // update state to trigger re-render
  }

  function handleCircleMouseOut(i, setTerritory, territory) {
    const newTerritory = [...territory];
    newTerritory[i].opacity = 1; // reset opacity of Image component
    setTerritory(newTerritory); // update state to trigger re-render
  }

  const loadImages = (imgPaths) => {
    const new_images = [...images];
    imgPaths.forEach((element) => {
      const image = new window.Image();
      image.src = element;
      new_images.push(image);
    });
    setImages(new_images);
  };

  useEffect(() => {
    if (firstRender) {
      setFirstRender(false);
      const stage = stageRef.current;
      stage.absolutePosition({ x: 0, y: -150 });
      const territory = createTerritory(25, 25);
      setTerritory(territory.regions);
      loadImages(territory.imgPaths);
    }
  }, []);

  const zoom = (e) => {
    var scaleBy = 1.1;
    const stage = stageRef.current;
    // stop default scrolling
    e.evt.preventDefault();

    var oldScale = stage.scaleX();
    var pointer = stage.getPointerPosition();

    var mousePointTo = {
      x: (pointer.x - stage.x()) / oldScale,
      y: (pointer.y - stage.y()) / oldScale,
    };

    // how to scale? Zoom in? Or zoom out?
    let direction = e.evt.deltaY > 0 ? 1 : -1;

    // when we zoom on trackpad, e.evt.ctrlKey is true
    // in that case lets revert direction
    if (e.evt.ctrlKey) {
      direction = -direction;
    }

    var newScale = direction > 0 ? oldScale * scaleBy : oldScale / scaleBy;
    if (newScale < 2 && newScale > 0.5) {
      stage.scale({ x: newScale, y: newScale });

      var newPos = {
        x: pointer.x - mousePointTo.x * newScale,
        y: pointer.y - mousePointTo.y * newScale,
      };
      stage.position(newPos);
    }
  };

  return (
    <Stage
      ref={stageRef}
      width={props.container.current.offsetWidth}
      height={props.container.current.offsetHeight}
      draggable={true}
      onWheel={zoom}
    >
      <Layer ref={layerRef}>
        {territory.map((region, i) => (
          <Image
            key={i}
            x={region.x - 50}
            y={region.y - 100}
            image={images.at(region.imageID) }
            onClick={() => {
              console.log(images);
            }}
            opacity={region.opacity}
          />
        ))}

        {territory.map((region, i) => {
          return (
              <>
            <Circle key={i}
                    x={region.x}
                    y={region.y}
                    radius={10}
                    fill= {region.color}
            />
                <Circle key={i}
                        x={region.x}
                        y={region.y}
                        radius={35}
                        fill="black"
                        opacity={0}
                        onMouseOver={() => handleCircleMouseOver(i, setTerritory, territory)}
                        onMouseOut={() => handleCircleMouseOut(i, setTerritory, territory)}
                />
              </>
        )})}
      </Layer>
    </Stage>
  );
}

export default Board;
