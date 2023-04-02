import Region from "../Classes/Region";

export const createTerritory = (m, n) => {
  const image = { height: 144, width: 96 };
  const territory = {
    imgPaths: [
      "tileset/tile000.png",
      "tileset/tile000.png",
      "tileset/tile000.png",
      "tileset/tile000.png",
      "tileset/tile001.png",
      "tileset/tile001.png",
      "tileset/tile001.png",
      "tileset/tile002.png",
      "tileset/tile003.png",
    ],
    regions: [],
  };
  for (let row = 0; row < m; row++) {
    for (let col = 0; col < n; col++) {
      const verticalDistance = image.height - 21 * 3;
      const horizontalDistance = image.width * (3 / 4) - 4.5;
      const offset = col % 2 == 0 ? image.width / 2 - 5 : 0;
      if (col % 2) {
        territory.regions.push(
          new Region(
            horizontalDistance * col,
            verticalDistance * row + offset - 18 * 3,
            "",
              1,
              "#FB2C00"
              ,false,
              Math.floor((Math.random()*territory.imgPaths.length))
          )
        );
      }
    }
    for (let col = 0; col < n; col++) {
      const verticalDistance = image.height - 21 * 3;
      const horizontalDistance = image.width * (3 / 4) - 4.5;
      const offset = col % 2 == 0 ? image.width / 2 - 5 : 0;
      if (!(col % 2)) {
        territory.regions.push(
          new Region(
            horizontalDistance * col,
            verticalDistance * row + offset - 18 * 3,
            "",2, "#003DFB"
              ,false,
              Math.floor((Math.random()*territory.imgPaths.length))
          )
        );
      }
    }
  }
  return territory;
};
