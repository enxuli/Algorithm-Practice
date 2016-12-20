class Solution {
public:
    vector<int> twoSum(vector<int> &numbers, int target) {
    unordered_map<int, int> map;
    vector<int> result;
    for(int i=0;i<numbers.size();i++){
        int seek=target-numbers[i];
        if(map.count(seek)>0){
        result.push_back(map[seek]);
        result.push_back(i);
        return result;
        }
        map[numbers[i]]=i;
    }
    return result;
    }
};
