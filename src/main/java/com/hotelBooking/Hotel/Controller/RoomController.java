package com.hotelBooking.Hotel.Controller;

import com.hotelBooking.Hotel.Services.RoomServices;
import com.hotelBooking.Hotel.Models.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomServices roomServices;

    private static final String IMAGE_DIRECTORY = "src/main/resources/uploaded_images/";

    @PostMapping("/add")
    public ResponseEntity<Rooms> addRoom(
            @RequestParam("roomType") String roomType,
            @RequestParam("roomPrice") int roomPrice,
            @RequestParam("roomPicture") MultipartFile roomPicture
    ) {
        try {
            // Create the directory if it does not exist
            File directory = new File(IMAGE_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String uniqueFilename = UUID.randomUUID().toString() + "_" + roomPicture.getOriginalFilename();
            Path path = Paths.get(IMAGE_DIRECTORY + uniqueFilename);
            Files.write(path, roomPicture.getBytes());

            Rooms rooms = new Rooms();
            rooms.setRoomType(roomType);
            rooms.setRoomPrice(roomPrice);
            rooms.setRoomPicture(uniqueFilename);
            rooms.setRoomStatus("Active");

            Rooms savedRooms = roomServices.saveRoom(rooms);

            return ResponseEntity.ok(savedRooms);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/get")
    public List<Rooms> getRooms() {
        try {
            return roomServices.getAllRooms();
        } catch (Exception e) {
            e.printStackTrace();
            return (List<Rooms>) ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path path = Paths.get(IMAGE_DIRECTORY).resolve(filename);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int roomId) {
        try {
            roomServices.deleteRoom(roomId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}
