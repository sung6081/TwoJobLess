package opgg.dto;

public class ParticipantDetailDTO {
    private String summonerName;
    private String riotGameName;
    private String riotTagLine;
    private String championName;
    private int champLevel;

    private int kills;
    private int deaths;
    private int assists;

    private int item0, item1, item2, item3, item4, item5, item6;
    private int spell1Id, spell2Id;

    private int mainRuneId;
    private int subRuneId;

    private String individualPosition;

    private int teamId;
    private boolean win;

    // --- Getters & Setters ---

    public String getSummonerName() {
        return summonerName;
    }
    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getRiotGameName() {
        return riotGameName;
    }
    public void setRiotGameName(String riotGameName) {
        this.riotGameName = riotGameName;
    }

    public String getRiotTagLine() {
        return riotTagLine;
    }
    public void setRiotTagLine(String riotTagLine) {
        this.riotTagLine = riotTagLine;
    }

    public String getChampionName() {
        return championName;
    }
    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public int getChampLevel() {
        return champLevel;
    }
    public void setChampLevel(int champLevel) {
        this.champLevel = champLevel;
    }

    public int getKills() {
        return kills;
    }
    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }
    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getItem0() {
        return item0;
    }
    public void setItem0(int item0) {
        this.item0 = item0;
    }
    public int getItem1() {
        return item1;
    }
    public void setItem1(int item1) {
        this.item1 = item1;
    }
    public int getItem2() {
        return item2;
    }
    public void setItem2(int item2) {
        this.item2 = item2;
    }
    public int getItem3() {
        return item3;
    }
    public void setItem3(int item3) {
        this.item3 = item3;
    }
    public int getItem4() {
        return item4;
    }
    public void setItem4(int item4) {
        this.item4 = item4;
    }
    public int getItem5() {
        return item5;
    }
    public void setItem5(int item5) {
        this.item5 = item5;
    }
    public int getItem6() {
        return item6;
    }
    public void setItem6(int item6) {
        this.item6 = item6;
    }

    public int getSpell1Id() {
        return spell1Id;
    }
    public void setSpell1Id(int spell1Id) {
        this.spell1Id = spell1Id;
    }
    public int getSpell2Id() {
        return spell2Id;
    }
    public void setSpell2Id(int spell2Id) {
        this.spell2Id = spell2Id;
    }

    public int getMainRuneId() {
        return mainRuneId;
    }
    public void setMainRuneId(int mainRuneId) {
        this.mainRuneId = mainRuneId;
    }

    public int getSubRuneId() {
        return subRuneId;
    }
    public void setSubRuneId(int subRuneId) {
        this.subRuneId = subRuneId;
    }

    public String getIndividualPosition() {
        return individualPosition;
    }
    public void setIndividualPosition(String individualPosition) {
        this.individualPosition = individualPosition;
    }

    public int getTeamId() {
        return teamId;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public boolean isWin() {
        return win;
    }
    public void setWin(boolean win) {
        this.win = win;
    }
}
