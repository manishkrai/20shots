package models;

import com.capitalnumbers.Views;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMatchRequest {	
	@Schema(description = "Match start time.", 
            example = "2022-11-22 11:00")
	@JsonView(Views.UpcomingMatchView.class)
	private String start_time;
	
	@Schema(description = "Name of the home team.", 
            example = "FC Chelsea")
	@JsonView(Views.UpcomingMatchView.class)
	private String home_team_name;
	
	@Schema(description = "Name of the away team.", 
            example = "Aston Villa FC")
	@JsonView(Views.UpcomingMatchView.class)
	private String away_team_name;
}
