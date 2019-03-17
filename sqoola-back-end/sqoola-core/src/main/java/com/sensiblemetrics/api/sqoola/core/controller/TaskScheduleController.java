package com.ubs.network.api.rest.core.controller;

import com.ubs.network.api.rest.core.controller.interfaces.ITaskScheduleController;
import com.ubs.network.api.rest.core.model.dto.TaskScheduleDTO;
import com.ubs.network.api.gateway.core.model.entities.TaskScheduleEntity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apress.domain.Vote;
import com.apress.dto.OptionCount;
import com.apress.dto.VoteResult;
import com.apress.repository.VoteRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController("taskScheduleController")
@RequestMapping("/v3/")
@Api(value = "computeresult", description = "Compute Results API")
public class TaskScheduleController<E extends TaskScheduleEntity, D extends TaskScheduleDTO> extends CoreBaseController<E, D> implements ITaskScheduleController<E, D> {
    @Inject
    private VoteRepository voteRepository;

    @RequestMapping(value="/computeresult", method=RequestMethod.GET)
    @ApiOperation(value = "Computes the results of a given Poll", response = VoteResult.class)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);

        // Algorithm to count votes
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
        for(Vote v : allVotes) {
            totalVotes ++;
            // Get the OptionCount corresponding to this Option
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if(optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount()+1);
        }

        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());

        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}