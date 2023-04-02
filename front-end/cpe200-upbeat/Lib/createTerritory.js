import Region from "../Classes/Region";

export const createTerritory = (m, n, regions) => {
  const image = { height: 144, width: 96 };
  const territory = {
    imgPaths: [
      "tileset/tile000.png",
      "tileset/tile001.png",
      "tileset/tile002.png",
      "tileset/tile003.png",
      "CityTile/tile010.png",
    ],
    regions: [],
  };
  for (let row = 0; row < m; row++) {
    for (let col = 0; col < n; col++) {
      const verticalDistance = image.height - 21 * 3;
      const horizontalDistance = image.width * (3 / 4) - 4.5;
      const offset = col % 2 == 0 ? image.width / 2 - 5 : 0;
      const region = regions[row][col];
      if (col % 2) {
        territory.regions.push(
          new Region(
            horizontalDistance * col,
            verticalDistance * row + offset - 18 * 3,
            "",
            region.empty ? "" : region.ownerID,
            region.color,
            region.cityCenter,
            region.tileID - 1
          )
        );
      }
    }
    for (let col = 0; col < n; col++) {
      const verticalDistance = image.height - 21 * 3;
      const horizontalDistance = image.width * (3 / 4) - 4.5;
      const offset = col % 2 == 0 ? image.width / 2 - 5 : 0;
      const region = regions[row][col];
      if (!(col % 2)) {
        territory.regions.push(
          new Region(
            horizontalDistance * col,
            verticalDistance * row + offset - 18 * 3,
            "",
            region.empty ? "" : region.ownerID,
            region.color,
            region.cityCenter,
            region.tileID - 1
          )
        );
      }
    }
  }
  return territory;
};
