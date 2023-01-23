package com.example.kte_labs.dto.response;
import com.example.kte_labs.dto.pair.EstimatesCount;
import java.util.List;

public class Product_Add_Information {
    private String description;
    private String averageEstimate;
    private Integer currentEstimateOfClient;
    private List<EstimatesCount> estimatesCount;

    public Product_Add_Information() {
    }

    public Product_Add_Information(String description,
                                   Integer currentEstimateOfClient) {
        this.description = description;
        this.currentEstimateOfClient = currentEstimateOfClient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAverageEstimate() {
        return averageEstimate;
    }

    public void setAverageEstimate(String averageEstimate) {
        this.averageEstimate = averageEstimate;
    }

    public Integer getCurrentEstimateOfClient() {
        return currentEstimateOfClient;
    }

    public void setCurrentEstimateOfClient(Integer currentEstimateOfClient) {
        this.currentEstimateOfClient = currentEstimateOfClient;
    }

    public List<EstimatesCount> getEstimatesCount() {
        return estimatesCount;
    }

    public void setEstimatesCount(List<EstimatesCount> estimatesCount) {
        this.estimatesCount = estimatesCount;
    }
}
