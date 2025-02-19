package ua.pidopryhora.mediaconverter.core;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ws.schild.jave.Encoder;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;

import java.io.File;
import java.util.Arrays;
@Slf4j
@Service
public class ConversionService {

    public ConversionService(){

    }

    public void convert(){
        boolean succeeded;
        try {
            File source = new File("file path");
            File target = new File("file path");

            VideoAttributes videoAttributes = new VideoAttributes();

            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(128000);
            audio.setChannels(2);
            audio.setSamplingRate(44100);

            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setOutputFormat("mp3");
            attrs.setAudioAttributes(audio);

            //Encode
            Encoder encoder = new Encoder();
            String formats = Arrays.toString(encoder.getSupportedEncodingFormats());
            String decodingFormats = Arrays.toString(encoder.getSupportedDecodingFormats());
            log.debug(formats);
            log.debug(decodingFormats);
//            encoder.encode(new MultimediaObject(source), target, attrs);

        } catch (Exception ex) {
            ex.printStackTrace();
            succeeded = false;
        }
    }



}
