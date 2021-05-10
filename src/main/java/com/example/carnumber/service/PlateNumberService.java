package com.example.carnumber.service;


import com.example.carnumber.model.PlateNumber;
import com.example.carnumber.repository.PlateNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PlateNumberService {
    private final PlateNumberRepository repository;
    private final List<Character> alphabet = Stream
            .of('А', 'Е', 'Т', 'О', 'Р', 'Н', 'У', 'К', 'Х', 'С', 'В', 'М')
            .sorted()
            .collect(Collectors.toList());


    public PlateNumber getRandom() {
        Random random = new Random();
        Character firstLetter = alphabet.get(random.nextInt(11));
        String number = "";
        StringBuilder oldNumber = new StringBuilder();
        int rnd = random.nextInt(1000);
        if (rnd < 100 && rnd > 10) {
            oldNumber.append(0).append(rnd);
        }
        if (rnd < 10) {
            oldNumber.append(0).append(0).append(rnd);
        } else {
            oldNumber.append(rnd);
        }
        Character secondLetter = alphabet.get(random.nextInt(11));
        Character thirdLetter = alphabet.get(random.nextInt(11));
        PlateNumber plateNumber = new PlateNumber(
                0,
                firstLetter,
                oldNumber.toString(),
                secondLetter,
                thirdLetter
        );
        StringBuilder generatedNumber = new StringBuilder();
        generatedNumber
                .append(firstLetter)
                .append(number)
                .append(secondLetter)
                .append(thirdLetter)
        ;

        while (repository.isExists(String.valueOf(generatedNumber))) {
            plateNumber = new PlateNumber(
                    0,
                    alphabet.get(random.nextInt(11)),
                    String.valueOf(random.nextInt(1000)),
                    alphabet.get(random.nextInt(11)),
                    alphabet.get(random.nextInt(11)))
            ;
        }
        return repository.save(plateNumber);
    }

    public PlateNumber getNext() {
        PlateNumber last = repository.getLast();
        int number = Integer.parseInt(last.getNumber());
        if (number == 999) {
            if (alphabet.indexOf(last.getThirdLetter()) == alphabet.size() - 1) {
                if (alphabet.indexOf(last.getSecondLetter()) == alphabet.size() - 1) {
                    if (alphabet.indexOf(last.getFirstLetter()) == alphabet.size() - 1) {
                        throw new RuntimeException();
                    }
                    return repository.save(new PlateNumber(
                            last.getId(),
                            alphabet.get(alphabet.indexOf(last.getFirstLetter()) + 1),
                            last.getNumber(),
                            last.getSecondLetter(),
                            last.getThirdLetter()
                    ));
                }
                return repository.save(new PlateNumber(
                        last.getId(),
                        last.getFirstLetter(),
                        last.getNumber(),
                        alphabet.get(alphabet.indexOf(last.getSecondLetter()) + 1),
                        last.getThirdLetter()
                ));
            }
            return repository.save(new PlateNumber(
                    last.getId(),
                    last.getFirstLetter(),
                    last.getNumber(),
                    last.getSecondLetter(),
                    alphabet.get(alphabet.indexOf(last.getThirdLetter()) + 1)
            ));
        }
        return repository.save(new PlateNumber(
                last.getId(),
                last.getFirstLetter(),
                String.valueOf((Integer.parseInt(last.getNumber()) + 1)),
                last.getSecondLetter(),
                last.getThirdLetter()
        ));
    }
}
