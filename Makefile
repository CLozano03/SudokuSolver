CXX = g++
CXXFLAGS = -std=c++11 -Wall

SRC_DIR = src
BUILD_DIR = build
SRCS = $(wildcard $(SRC_DIR)/*.cpp)
OBJS = $(patsubst $(SRC_DIR)/%.cpp,$(BUILD_DIR)/%.o,$(SRCS))
EXEC = $(BUILD_DIR)/sudoku_solve

all: $(EXEC)

$(EXEC): $(OBJS)
	@mkdir -p $(BUILD_DIR)
	$(CXX) $(CXXFLAGS) $(OBJS) -o $(EXEC)

$(BUILD_DIR)/%.o: $(SRC_DIR)/%.cpp
	@mkdir -p $(BUILD_DIR)
	$(CXX) $(CXXFLAGS) -c $< -o $@

clean:
	rm -rf $(BUILD_DIR)
