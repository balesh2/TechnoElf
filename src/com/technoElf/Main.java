package com.technoElf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        List<PersonData> adults = new ArrayList<>();
        List<PersonData> kids = new ArrayList<>();

        int currentYear = Date.from(Instant.now()).getYear();
	    String prevAdultsFileName = "~/workspace/technoElf/adults-" + currentYear + ".txt";
	    String prevKidsFileName = "~/workspace/technoElf/kids-" + currentYear + ".txt";

        FileReader prevAdultsFileReader = new FileReader(prevAdultsFileName);
        FileReader prevKidsFileReader = new FileReader(prevKidsFileName);

        BufferedReader prevAdultsFile = new BufferedReader(prevAdultsFileReader);
        BufferedReader prevKidsFile = new BufferedReader(prevKidsFileReader);

        Stream<String> prevAdultsFileStream = prevAdultsFile.lines();
        Stream<String> prevKidsFileStream = prevKidsFile.lines();

        adults.addAll(processPersonFileStream(prevAdultsFileStream));
        kids.addAll(processPersonFileStream(prevKidsFileStream));
    }

    private static List<PersonData> processPersonFileStream(Stream<String> personStream) {
        List<PersonData> people = new ArrayList<>();

        personStream.forEach((personString) -> {
            String[] personInfo = personString.split(" ");
            people.add(new PersonData(personInfo));
        });

        return people;
    }

    private static class PersonData {
        private String code;
        private String name;
        private String email;
        private String partnerCode;
        private String prevPersonCode;
        private String targetCode;

        private PersonData(String[] personInfo) {
            code = personInfo[0];
            name = personInfo[1];
            email = personInfo[2];
            partnerCode = personInfo[3];
            prevPersonCode = personInfo[4];
            targetCode = null;
        }
    }
}
