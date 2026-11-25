package Networking;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchHistory {

    private final Path filePath = Path.of("src", "Networking", "match-history.txt");

    public void saveMatch(MatchResult result) throws IOException {

        String matchData =
                result.getPlayerName() + "," +
                        result.getPlayerCharacter() + "," +
                        result.getComputerCharacter() + "," +
                        result.getWinner() + "," +
                        result.getRounds() + "," +
                        result.getDateTime() +
                        System.lineSeparator();

        Files.createDirectories(filePath.getParent());

        Files.writeString(
                filePath,
                matchData,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    public List<MatchResult> getMatchHistory() throws IOException {

        if (!Files.exists(filePath)) {
            return List.of();
        }

        ArrayList<MatchResult> matchResults = new ArrayList<>();

        for (String line : Files.readAllLines(filePath)) {

            String[] data = line.split(",", -1);

            if (data.length == 6) {

                MatchResult result = new MatchResult(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        Integer.parseInt(data[4]),
                        LocalDateTime.parse(data[5])
                );

                matchResults.add(result);
            }
        }

        return matchResults;
    }
}
