package com.ILSI.TouristeProject.Activitis.Service.ServiceImpl;

import com.ILSI.TouristeProject.Activitis.Activity;
import com.ILSI.TouristeProject.Activitis.ActivityDto;
import com.ILSI.TouristeProject.AutreClass.Service.ImageService;
import com.ILSI.TouristeProject.AutreClass.model.Image;
import com.ILSI.TouristeProject.Locations.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ImageService imageService;

    public void addImagesToActivity(Activity activity, List<MultipartFile> images) throws IOException {
        for (MultipartFile file : images) {
            String url = imageService.saveImage(file);
            Image image = new Image();
            image.setImageUrl(url);
            image.setActivity(activity);
            activity.addImage(image);
        }
    }


    public void getActivity(ActivityDto activityDto, Activity activity) throws IOException {
        activity.setName(activityDto.getName());
        activity.setDescription(activityDto.getDescription());
        activity.setAgeLimit(activityDto.getAgeLimit());
        activity.setDuration(activityDto.getDuration());
        activity.setPrice(activityDto.getPrice());
        this.addImagesToActivity(activity, activityDto.getImages());
    }
}
