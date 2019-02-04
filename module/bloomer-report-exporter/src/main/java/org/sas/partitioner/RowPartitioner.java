package org.sas.partitioner;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Objects.nonNull;

/**
 * @author Vivek Tiwari
 */
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class RowPartitioner implements Partitioner {

	private final JdbcOperations jdbcTemplate;
	private final String table;
	private final String column;

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		Integer maxOffSet = jdbcTemplate.queryForObject(format(QueryConstants.MAX_OFFSET_QUERY, column, table), Integer.class);

		Map<String, ExecutionContext> result = new HashMap<>();
		Integer offSet = 0;

		if (nonNull(maxOffSet) && maxOffSet > 0) {
			int limit = maxOffSet / gridSize + 1;
			int number = 0;
			boolean firstQuery = true;

			while (offSet < maxOffSet) {
				ExecutionContext value = new ExecutionContext();
				result.put("partition" + number, value);

				if (firstQuery) {
					offSet = jdbcTemplate.queryForObject(format(QueryConstants.FIRST_PAGE_OFFSET_QUERY, column, column, table, limit), Integer.class);
					value.putString("query", QueryConstants.FIRST_PAGE_QUERY);
					value.putLong("maxId", offSet);
					firstQuery = false;
				} else {
					value.putLong("minId", offSet);
					offSet = jdbcTemplate.queryForObject(format(QueryConstants.REMAINING_PAGE_OFFSET_QUERY, column, column, table, column, offSet, limit), Integer.class);
					value.putString("query", QueryConstants.REMAINING_PAGE_QUERY);
					value.putLong("maxId", offSet);
				}

				number++;
			}
		}
		return result;
	}

	@Getter
	private static class QueryConstants {
		private static final String MAX_OFFSET_QUERY = "SELECT MAX(%s) FROM %s";
		private static final String FIRST_PAGE_OFFSET_QUERY = "SELECT MAX(tb.%s) FROM (SELECT %s FROM %s LIMIT %s) tb";
		private static final String REMAINING_PAGE_OFFSET_QUERY = "SELECT MAX(tb.%s) FROM (SELECT %s FROM %s WHERE %s > %s LIMIT %s) tb";
		private static final String FIRST_PAGE_QUERY = "FROM ClassroomEntity As classroomEntity where classroomEntity.id <= :maxId";
		private static final String REMAINING_PAGE_QUERY = "FROM ClassroomEntity As classroomEntity where classroomEntity.id > :minId AND classroomEntity.id <= :maxId";

	}

}
