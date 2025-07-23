package opgg.dto;

import java.util.List;

import opgg.champion.RiotChampion;

public class ParticipantOfSpectatorDTO {

	//Field
	private RiotAccountDTO riotAccountDTO; //유저 정보
	private RankDTO rankDTO; //랭크 정보(언랭 제외)
	private long teamId; //레드팀 블루팀
	private List<SpellDTO> spells; //스펠 1,2
	private RiotChampion champ; //선택한 챔프
	private long perkStyle; //주룬
	private long perkSubStyle; //부룬
	private PerkDTO perk; //주룬 디테일
	private PerkDTO subPerk; //부룬 디테일
	
	//Constructor
	public ParticipantOfSpectatorDTO() {
		
	}

	public ParticipantOfSpectatorDTO(RiotAccountDTO riotAccountDTO, RankDTO rankDTO, long teamId, List<SpellDTO> spells,
			RiotChampion champ, long perkStyle, long perkSubStyle, PerkDTO perk, PerkDTO subPerk) {
		super();
		this.riotAccountDTO = riotAccountDTO;
		this.rankDTO = rankDTO;
		this.teamId = teamId;
		this.spells = spells;
		this.champ = champ;
		this.perkStyle = perkStyle;
		this.perkSubStyle = perkSubStyle;
		this.perk = perk;
		this.subPerk = subPerk;
	}

	//Getter & Setter
	public RiotAccountDTO getRiotAccountDTO() {
		return riotAccountDTO;
	}

	public void setRiotAccountDTO(RiotAccountDTO riotAccountDTO) {
		this.riotAccountDTO = riotAccountDTO;
	}

	public RankDTO getRankDTO() {
		return rankDTO;
	}

	public void setRankDTO(RankDTO rankDTO) {
		this.rankDTO = rankDTO;
	}

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public List<SpellDTO> getSpells() {
		return spells;
	}

	public void setSpells(List<SpellDTO> spells) {
		this.spells = spells;
	}

	public RiotChampion getChamp() {
		return champ;
	}

	public void setChamp(RiotChampion champ) {
		this.champ = champ;
	}

	public long getPerkStyle() {
		return perkStyle;
	}

	public void setPerkStyle(long perkStyle) {
		this.perkStyle = perkStyle;
	}

	public long getPerkSubStyle() {
		return perkSubStyle;
	}

	public void setPerkSubStyle(long perkSubStyle) {
		this.perkSubStyle = perkSubStyle;
	}

	public PerkDTO getPerk() {
		return perk;
	}

	public void setPerk(PerkDTO perk) {
		this.perk = perk;
	}

	public PerkDTO getSubPerk() {
		return subPerk;
	}

	public void setSubPerk(PerkDTO subPerk) {
		this.subPerk = subPerk;
	}

	@Override
	public String toString() {
		return "\nParticipantOfSpectatorDTO [riotAccountDTO=" + riotAccountDTO + ", rankDTO=" + rankDTO + ", teamId="
				+ teamId + ",\n spells=" + spells + ",\n champ=" + champ + ",\n perkStyle=" + perkStyle + ", perkSubStyle="
				+ perkSubStyle + ",\n perk=" + perk + ",\n subPerk=" + subPerk + "]";
	}
	
}
