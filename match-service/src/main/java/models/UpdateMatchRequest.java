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
public class UpdateMatchRequest {
	@Schema(description = "Unique identifier of the Match.", 
            example = "1")
	private Long id;	
	
	@Schema(description = "Match start time.", 
            example = "2022-11-22 11:00")
	@JsonView(Views.UpcomingMatchView.class)
	private String start_time;
}
