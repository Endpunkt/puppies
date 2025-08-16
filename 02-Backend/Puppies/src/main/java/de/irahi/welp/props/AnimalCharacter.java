package de.irahi.welp.props;



public enum AnimalCharacter{
	Aggressive("Aggressive"),
	Calm("Calm"),
	Curious("Curious"),
	Fearful("Fearful"),
	Friendly("Friendly"),
	Independent("Independent"),
	Loyal("Loyal"),
	None("None"),
	Playful("Playful"),
	Protective("Protective"),
	Reasonable("Reasonable"),
	Social("Social"),
	Vigorous("Vigorous");
	

	
	private final String characterName;
	
	AnimalCharacter(String characterName){
		this.characterName = characterName;
	}
	
	public String getCharacterName() {	return this.characterName;	}
	
}
