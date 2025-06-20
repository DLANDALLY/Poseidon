package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class BidTests {

	@Autowired
	private BidListRepository bidListRepository;

	@Test
	public void bidListTest() {
		BidList bid = BidList.builder()
				.account("Account Test")
				.type("Type Test")
				.bidQuantity(10d)
				.build();

		// Save
		bid = bidListRepository.save(bid);

		assertNotNull(bid.getBidListId());
		assertEquals(10d, bid.getBidQuantity(), 10d);

		// Update
		bid.setBidQuantity(20d);
		bid = bidListRepository.save(bid);
		assertEquals(20d, bid.getBidQuantity(), 20d);

		// Find
		List<BidList> listResult = bidListRepository.findAll();
        assertFalse(listResult.isEmpty());

		// Delete
		Integer id = Integer.valueOf(bid.getBidListId());
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		assertFalse(bidList.isPresent());
	}
}
